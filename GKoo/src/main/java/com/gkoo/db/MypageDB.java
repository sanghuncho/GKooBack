package com.gkoo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.BuyingOrderData;
import com.gkoo.data.DeliveryKoreaData;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import com.gkoo.exception.MypageException;
import databaseUtil.ConnectionDB;
import payment.PaymentData;

public class MypageDB {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static final String FETTCH_ORDER_DATA = "SELECT os.orderid, os.ship_price, os.ship_state, os.trackingnr_kor, os.trackingnr_world, os.order_date, rp.name_kor "
            + " FROM ORDERSTATE os, RECIPIENT rp WHERE rp.orderid=os.orderid AND os.userid=? ORDER BY orderid DESC";
    
    private static final String UPDATE_TRACKNG_NUMBER = "UPDATE orderstate SET trackingnr_world = ?, tracking_company_world = ? where userid = ? and orderid = ?";

    private static final String GET_PERSONAL_BOX_ADDRESS = "SELECT personal_box_address FROM CUSTOMER WHERE userid = ?";
    
    public static String getPersonalBoxAddress(String userid) {
        String personalBoxAddress = "";
        ResultSet resultSet = null;
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(GET_PERSONAL_BOX_ADDRESS);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            personalBoxAddress = writePersonalBoxAddress(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching personal box address";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        return personalBoxAddress;
    }
    
    private static String writePersonalBoxAddress(ResultSet rs) throws SQLException {
        String personalBoxAddress = "";
        while (rs.next()) {
            personalBoxAddress = rs.getString("personal_box_address");
        }
        return personalBoxAddress;
    }
    
    public static List<OrderInformation> getOrderData(String userid) {
        List<OrderInformation> orderInformationList = new ArrayList<>();
        ResultSet resultSet = null;
        
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(FETTCH_ORDER_DATA);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            orderInformationList = writeOrderInformation(resultSet, orderInformationList);
        } catch (SQLException e) {
            String error = "Error fetching orderdata";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        return orderInformationList;
    }
    
    private static List<OrderInformation> writeOrderInformation(ResultSet rs, List<OrderInformation> orderInformationList) throws SQLException {
        while (rs.next()) {
            OrderInformation orderInfo = new OrderInformation();
            orderInfo.setOrderid(rs.getString("orderid"));
            orderInfo.setProductInfo(collectProductInfos(orderInfo.getOrderid()));
            orderInfo.setRecipient(rs.getString("name_kor"));
            orderInfo.setDeliveryPayment(rs.getDouble("ship_price"));
            orderInfo.setDeliveryState(rs.getInt("ship_state"));
            orderInfo.setDeliveryTracking(rs.getString("trackingnr_world"));
            orderInfo.setOrderDate(rs.getDate("order_date"));
            orderInformationList.add(orderInfo);
        }
        return orderInformationList;
    }
    
    private static String collectProductInfos(String orderId) {
        List<String> products = new ArrayList<>();
        ResultSet resultSet = null;
        
        final String GET_PRODUCTS_NAME = "SELECT pd_itemtitle FROM PRODUCT WHERE orderid=?";
        
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(GET_PRODUCTS_NAME);){
            psmt.setString(1, orderId);
            resultSet = psmt.executeQuery();
            while (resultSet.next()) {
                products.add(resultSet.getString("pd_itemtitle"));
            }
        } catch (SQLException e) {
            String error = "Error fetching product information";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }

        return products.toString().replace("[", "").replace("]", "");
    }
    
    public static List<WarehouseInformation> getWarehouseData(String userid) {
        ResultSet resultSet = null;
        ConnectionDB.connectSQL();
        String query = "SELECT os.orderid, os.ship_price, os.ship_state, os.trackingnr_world, os.tracking_company_world, rp.name_kor "
                + " FROM ORDERSTATE os, RECIPIENT rp WHERE rp.orderid=os.orderid AND os.userid=? AND (os.ship_state=1 or os.ship_state=2) ORDER BY orderid DESC";
                
        List<WarehouseInformation> warehouseInformationList = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(query);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            warehouseInformationList = writeWarehouseInformation(resultSet, warehouseInformationList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return warehouseInformationList;
    }
    
    private static List<WarehouseInformation> writeWarehouseInformation(ResultSet rs, List<WarehouseInformation> warehouseInformationList) throws SQLException {
        while (rs.next()) {
            WarehouseInformation warehouseInfo = new WarehouseInformation();
            warehouseInfo.setOrderid(rs.getString("orderid"));
            warehouseInfo.setProductInfo(collectProductInfos(warehouseInfo.getOrderid()));
            warehouseInfo.setRecipient(rs.getString("name_kor"));
            warehouseInfo.setDeliveryPayment(rs.getDouble("ship_price"));
            warehouseInfo.setDeliveryState(rs.getInt("ship_state"));
            warehouseInfo.setDeliveryTracking(convertTrackingStatus(rs.getString("tracking_company_world"), rs.getString("trackingnr_world")));
            warehouseInformationList.add(warehouseInfo);
        }
        return warehouseInformationList;
    }
    
    private static String convertTrackingStatus(String company, String number) {
        if(company == null || number == null) {
            return "default";
        } else {
            return (company + " : " + number);
        }
    }
    
    public static ResponseEntity<?> updateTrackingNumber(String userid, String orderid, String trackingCompony, String trackingNumber) {
        ConnectionDB.connectSQL();
        
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(UPDATE_TRACKNG_NUMBER);){
            psmt.setString(1, trackingNumber);
            psmt.setString(2, trackingCompony);
            psmt.setString(3, userid);
            psmt.setString(4, orderid);
            psmt.executeUpdate();
        } catch (SQLException e) {
            String error = "Error updating trackingnumber";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
    
    public static List<PaymentData> getPaymentData(String userid) {
        ConnectionDB.connectSQL();
        final String GET_PAYMENTDATA = "SELECT * FROM PAYMENT WHERE (payment_state = 1 or payment_state = 2 or payment_state = 3) AND userid=? ORDER BY orderid DESC";
        ResultSet resultSet = null;
        List<PaymentData> paymentDataList = null;
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(GET_PAYMENTDATA);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            paymentDataList = writePaymentData(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching paymentData";
            LOGGER.error(error, e);
        }
        return paymentDataList;
    }
    
    private static List<PaymentData> writePaymentData(ResultSet rs){
        List<PaymentData> paymentDataList = new ArrayList<>();
        try {
            while (rs.next()) {
                PaymentData payment = new PaymentData();
                try {
                    payment.setPaymentid(rs.getInt("paymentid"));
                    payment.setOrderid(rs.getString("orderid"));
                    payment.setPaymentState(rs.getInt("payment_state"));
                } catch (SQLException e) {
                    String error = "Error fetching paymentData";
                    LOGGER.error(error, e);
                }
                paymentDataList.add(payment);
            }
        } catch (SQLException e) {
            String error = "Error fetching paymentData";
            LOGGER.error(error, e);
        }
        return paymentDataList;
    }
    
    public static List<DeliveryKoreaData> getDeliveryKoreaData(String userid) {
        ConnectionDB.connectSQL();
        final String GET_DELIVERYKOREADATA = "SELECT * FROM ORDERSTATE WHERE ship_state > 4 AND userid=? ORDER BY orderid DESC";
        ResultSet resultSet = null;
        List<DeliveryKoreaData> deliveryKoreaDataList = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(GET_DELIVERYKOREADATA);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            deliveryKoreaDataList = writeDeliveryKoreaData(resultSet, deliveryKoreaDataList);
        } catch (SQLException e) {
            String error = "Error fetching delivery korea data";
            LOGGER.error(error, e);
        }
        return deliveryKoreaDataList;
    }
    
    private static List<DeliveryKoreaData> writeDeliveryKoreaData(ResultSet rs, List<DeliveryKoreaData> deliveryKoreaDataList){
        try {
            while (rs.next()) {
                DeliveryKoreaData deliveryKoreaData = new DeliveryKoreaData();
                try {
                    deliveryKoreaData.setId(rs.getInt("id"));
                    deliveryKoreaData.setOrderid(rs.getString("orderid"));
                    deliveryKoreaData.setDeliveryState(rs.getInt("ship_state"));
                    deliveryKoreaData.setDeliveryTracking(rs.getString("trackingnr_kor"));
                } catch (SQLException e) {
                    String error = "Error fetching delivery korea data";
                    LOGGER.error(error, e);
                }
                deliveryKoreaDataList.add(deliveryKoreaData);
            }
        } catch (SQLException e) {
            String error = "Error fetching delivery korea data";
            LOGGER.error(error, e);
        }
        return deliveryKoreaDataList;
    }
    
    /////////////////////
    /// BuyingService ///
    /////////////////////
    private static final String FETTCH_ORDER_DATA_BUYINGSERVICE = "SELECT bs.object_id, bs.orderid, bs.buying_price, bs.ship_price, "
            + "bs.buying_service_state, bs.order_date, bs.main_image_url FROM BUYING_SERVICE bs WHERE bs.userid=? ORDER BY bs.orderid DESC";
    
    private static final String GET_PRODUCTS_NAME = "SELECT pd_itemname FROM BUYING_SERVICE_PRODUCT WHERE fk_buying_service = ? ";

    public static List<BuyingOrderData> getOrderDataBuyingService(String userid) {
        List<BuyingOrderData> buyingOrderDataList = new ArrayList<>();
        ResultSet resultSet = null;
        
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(FETTCH_ORDER_DATA_BUYINGSERVICE);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            buyingOrderDataList = writeBuyingOrderData(resultSet, buyingOrderDataList);
        } catch (SQLException e) {
            String error = "Error fetching orderdata";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        
        collectProductData(buyingOrderDataList);
        
        return buyingOrderDataList;
    }
    
    private static List<BuyingOrderData> writeBuyingOrderData(ResultSet rs, List<BuyingOrderData> buyingOrderDataList) throws SQLException {
        while (rs.next()) {
            BuyingOrderData buyingOrderData = new BuyingOrderData();
            buyingOrderData.setObjectid(rs.getInt("object_id"));
            buyingOrderData.setOrderid(rs.getString("orderid"));
            buyingOrderData.setBuyingPrice(rs.getDouble("buying_price"));
            buyingOrderData.setDeliveryPayment(rs.getDouble("ship_price"));
            buyingOrderData.setBuyingServiceState(rs.getInt("buying_service_state"));
            buyingOrderData.setOrderDate(rs.getDate("order_date"));
            buyingOrderData.setMainImageUrl(rs.getString("main_image_url"));
            
            buyingOrderDataList.add(buyingOrderData);
        }
        return buyingOrderDataList;
    }
    
    private static void collectProductData(List<BuyingOrderData> buyingOrderDataList) {
        
        for(BuyingOrderData data : buyingOrderDataList) {
            List<String> products = new ArrayList<>();
            
            ConnectionDB.connectSQL();
            ResultSet resultSet = null;
            try (Connection conn = ConnectionDB.getConnectInstance();
                    PreparedStatement psmt = conn.prepareStatement(GET_PRODUCTS_NAME);){
                psmt.setInt(1, data.getObjectid());
                resultSet = psmt.executeQuery();
                while (resultSet.next()) {
                    products.add(resultSet.getString("pd_itemname"));
                }
            } catch (SQLException e) {
                String error = "Error fetching product information";
                LOGGER.error(error, e);
                throw new MypageException(error, e);
            }
            data.setProductInfo(products.toString().replace("[", "").replace("]", ""));
        }
        //return products.toString().replace("[", "").replace("]", "");
    }
    
    public static List<PaymentData> getPaymentProductBuyingService(String userid) {
        ConnectionDB.connectSQL();
        final String GET_PAYMENTDATA = "SELECT bsp.object_id, bsp.buying_service_payment_state, bsp.buying_deposit_ownername, bsp.payment_art,"
                + "bs.orderid, bs.buying_price FROM BUYING_SERVICE_PAYMENT bsp, BUYING_SERVICE bs WHERE bs.userid=? and bs.object_id=bsp.fk_buying_service "
                + "and (bsp.buying_service_payment_state = 1 or bsp.buying_service_payment_state = 2 or bsp.buying_service_payment_state = 3 or bsp.buying_service_payment_state = 4 or bsp.buying_service_payment_state = 5) ORDER BY bs.orderid DESC";
        ResultSet resultSet = null;
        List<PaymentData> paymentDataList = null;
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(GET_PAYMENTDATA);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            paymentDataList = writePaymentDataBuyingService(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching paymentData";
            LOGGER.error(error, e);
        }
        return paymentDataList;
    }
    
    private static List<PaymentData> writePaymentDataBuyingService(ResultSet rs){
        List<PaymentData> paymentDataList = new ArrayList<>();
        try {
            while (rs.next()) {
                PaymentData payment = new PaymentData();
                try {
                    payment.setPaymentid(rs.getInt("object_id"));
                    payment.setOrderid(rs.getString("orderid"));
                    payment.setPaymentState(rs.getInt("buying_service_payment_state"));
                    payment.setBuyingPrice(rs.getDouble("buying_price"));
                    payment.setPaymentOwnername(rs.getString("buying_deposit_ownername"));
                    payment.setPaymentArt(rs.getInt("payment_art"));
                } catch (SQLException e) {
                    String error = "Error fetching paymentData";
                    LOGGER.error(error, e);
                }
                paymentDataList.add(payment);
            }
        } catch (SQLException e) {
            String error = "Error fetching paymentData";
            LOGGER.error(error, e);
        }
        return paymentDataList;
    }

    private static List<PaymentData> writePaymentDeliveryBuyingService(ResultSet rs){
        List<PaymentData> paymentDataList = new ArrayList<>();
        try {
            while (rs.next()) {
                PaymentData payment = new PaymentData();
                try {
                    payment.setPaymentid(rs.getInt("object_id"));
                    payment.setOrderid(rs.getString("orderid"));
                    payment.setPaymentState(rs.getInt("buying_service_payment_state"));
                    payment.setShipPrice(rs.getDouble("ship_price"));
                    payment.setBoxActualWeight(rs.getDouble("box_actual_weight"));
                    payment.setBoxVolumeWeight(rs.getDouble("box_volume_weight"));
                    payment.setPaymentOwnername(rs.getString("shipping_deposit_ownername"));
                    payment.setPaymentArt(rs.getInt("payment_art_shipping_price"));
                } catch (SQLException e) {
                    String error = "Error fetching paymentDeliveryData";
                    LOGGER.error(error, e);
                }
                paymentDataList.add(payment);
            }
        } catch (SQLException e) {
            String error = "Error fetching paymentDeliveryData";
            LOGGER.error(error, e);
        }
        return paymentDataList;
    }
    
    public static List<PaymentData> getPaymentDeliveryBuyingService(String userid) {
        ConnectionDB.connectSQL();
        final String GET_PAYMENTDATA = "SELECT bsp.object_id, bsp.buying_service_payment_state, bsp.shipping_deposit_ownername, bsp.payment_art_shipping_price, bs.orderid, bs.ship_price, bs.box_actual_weight, bs.box_volume_weight FROM BUYING_SERVICE_PAYMENT bsp, BUYING_SERVICE bs WHERE bs.userid=? "
                + "and bs.object_id=bsp.fk_buying_service and (bsp.buying_service_payment_state = 3 or bsp.buying_service_payment_state = 4 or bsp.buying_service_payment_state = 5) ORDER BY bs.orderid DESC";
        
        ResultSet resultSet = null;
        List<PaymentData> paymentDataList = null;
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(GET_PAYMENTDATA);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            paymentDataList = writePaymentDeliveryBuyingService(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching paymentData";
            LOGGER.error(error, e);
        }
        return paymentDataList;
    }
    
    public static List<DeliveryKoreaData> getDeliveryKoreaDataBuyingService(String userid) {
        ConnectionDB.connectSQL();
        final String GET_DELIVERYKOREADATA = "SELECT * FROM BUYING_SERVICE WHERE buying_service_state > 4 AND userid=? ORDER BY orderid DESC";
        ResultSet resultSet = null;
        List<DeliveryKoreaData> deliveryKoreaDataList = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(GET_DELIVERYKOREADATA);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            deliveryKoreaDataList = writeDeliveryKoreaDataBuyingService(resultSet, deliveryKoreaDataList);
        } catch (SQLException e) {
            String error = "Error fetching delivery korea data";
            LOGGER.error(error, e);
        }
        return deliveryKoreaDataList;
    }
    
    private static List<DeliveryKoreaData> writeDeliveryKoreaDataBuyingService(ResultSet rs, List<DeliveryKoreaData> deliveryKoreaDataList){
        try {
            while (rs.next()) {
                DeliveryKoreaData deliveryKoreaData = new DeliveryKoreaData();
                try {
                    deliveryKoreaData.setId(rs.getInt("object_id"));
                    deliveryKoreaData.setOrderid(rs.getString("orderid"));
                    deliveryKoreaData.setDeliveryState(rs.getInt("buying_service_state"));
                    deliveryKoreaData.setDeliveryTracking(rs.getString("trackingnr_kor"));
                } catch (SQLException e) {
                    String error = "Error fetching delivery korea data";
                    LOGGER.error(error, e);
                }
                deliveryKoreaDataList.add(deliveryKoreaData);
            }
        } catch (SQLException e) {
            String error = "Error fetching delivery korea data";
            LOGGER.error(error, e);
        }
        return deliveryKoreaDataList;
    }
    
    public static ResponseEntity<?> updatePaymentProductBuyingService(int objectid, String paymentOwnername, int paymentArt) {
        ConnectionDB.connectSQL();
        final String UPDATE_PAYMENT_DEPOSIT_OWNERNAME = 
                "UPDATE BUYING_SERVICE_PAYMENT SET buying_deposit_ownername = ? , payment_art = ? WHERE object_id = ?";
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(UPDATE_PAYMENT_DEPOSIT_OWNERNAME);){
            psmt.setString(1, paymentOwnername);
            psmt.setInt(2, paymentArt);
            psmt.setInt(3, objectid);
            psmt.executeUpdate();
        } catch (SQLException e) {
            String error = "Error updating of payment deposit ownername";
            LOGGER.error(error, e);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }

    public static ResponseEntity<?> updatePaymentDeliveryBuyingService(int objectid, String paymentOwnername, int paymentArt) {
        ConnectionDB.connectSQL();
        final String UPDATE_PAYMENT_DEPOSIT_OWNERNAME = 
                "UPDATE BUYING_SERVICE_PAYMENT SET shipping_deposit_ownername = ?, payment_art_shipping_price = ? WHERE object_id = ?";
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(UPDATE_PAYMENT_DEPOSIT_OWNERNAME);){
            psmt.setString(1, paymentOwnername);
            psmt.setInt(2, paymentArt);
            psmt.setInt(3, objectid);
            psmt.executeUpdate();
        } catch (SQLException e) {
            String error = "Error updating of payment shipping deposit ownername";
            LOGGER.error(error, e);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
}
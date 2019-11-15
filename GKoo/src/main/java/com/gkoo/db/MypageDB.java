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
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import com.gkoo.exception.MypageException;
import databaseUtil.ConnectionDB;

public class MypageDB {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static final String FETTCH_ORDER_DATA = "SELECT os.orderid, os.ship_price, os.ship_state, os.trackingnr_kor, os.trackingnr_world, rp.name_kor "
            + " FROM ORDERSTATE os, RECIPIENT rp WHERE rp.orderid=os.orderid AND os.memberid=?";
    private static final String UPDATE_TRACKNG_NUMBER = "UPDATE orderstate SET trackingnr_world = ?, tracking_company_world = ? where memberid = ? and orderid = ?";

    
    public static List<OrderInformation> getOrderData(String userid) {
        ConnectionDB.connectSQL();
        List<OrderInformation> orderInformationList = new ArrayList<>();
        ResultSet resultSet = null;
        
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
            orderInfo.setOrderNumber(rs.getString("orderid"));
            orderInfo.setProductInfo(collectProductInfos(orderInfo.getOrderNumber()));
            orderInfo.setRecipient(rs.getString("name_kor"));
            orderInfo.setDeliveryPayment(rs.getDouble("ship_price"));
            orderInfo.setDeliveryState(rs.getInt("ship_state"));
            orderInfo.setDeliveryTracking(rs.getString("trackingnr_world"));
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
                + " FROM ORDERSTATE os, RECIPIENT rp WHERE rp.orderid=os.orderid AND os.memberid=? AND (os.ship_state=1 or os.ship_state=2)";
                
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
            warehouseInfo.setOrderNumber(rs.getString("orderid"));
            warehouseInfo.setProductInfo(collectProductInfos(warehouseInfo.getOrderNumber()));
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
    
    public static ResponseEntity<?> updateTrackingNumber(String memberId, String orderNumber, String trackingCompony, String trackingNumber) {
        ConnectionDB.connectSQL();
        
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(UPDATE_TRACKNG_NUMBER);){
            psmt.setString(1, trackingNumber);
            psmt.setString(2, trackingCompony);
            psmt.setString(3, memberId);
            psmt.setString(4, orderNumber);
            psmt.executeUpdate();
        } catch (SQLException e) {
            String error = "Error updating trackingnumber";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
}

package com.gkoo.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import databaseUtil.ConnectionDB;
import mypage.information.ProductsCommonInformation;
import mypage.information.ProductsInformation;
import mypage.information.ProductsInformation.Product;
import mypage.information.RecipientData;
import shippingService.DeliveryDataObject;
import shippingService.ShippingProduct;
import shippingService.ShippingServiceDAO;
import shippingService.ShippingServiceModel;

/**
 *
 * @author sanghuncho
 *
 * @since  18.11.2019
 *
 */
public class MypageDetailDB {
    private static final Logger LOGGER = LogManager.getLogger();
    
    public static RecipientData getRecipientInfo(String username, String number) {
        ResultSet resultSet = null;
        ConnectionDB.connectSQL();
        String query = "SELECT * FROM RECIPIENT WHERE memberid=? AND orderid=?";
        RecipientData recipient = new RecipientData();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(query);){
            psmt.setString(1, username);
            psmt.setString(2, number);
            resultSet = psmt.executeQuery();
            recipient = writeRecipientInformation(resultSet, recipient);
        } catch (SQLException e) {
            //Logger
        }       
        return recipient;
    }
    
    public static RecipientData writeRecipientInformation(ResultSet rs, RecipientData recipient) throws SQLException {
        while (rs.next()) {
            recipient.setNameKor(rs.getString("name_kor"));
            recipient.setNameEng(rs.getString("name_eng"));
            recipient.setTransitNr(rs.getString("transit_nr"));
            recipient.setPhonePrefic(rs.getString("phone_prefic"));
            recipient.setPhoneInterfix(rs.getString("phone_interfix"));
            recipient.setPhoneSuffix(rs.getString("phone_suffix"));
            recipient.buildPhoneNr();
            recipient.setZipCode(rs.getString("zip_code"));
            recipient.setAddress(rs.getString("address"));
            recipient.setAddressDetails(rs.getString("detail_address"));
            //recipient.buildFullAdress();
            recipient.setUsercomment(rs.getString("usercomment"));
        }
        return recipient;
    }
    
    public static ProductsCommonInformation getProductsCommonInfo(String username, String orderNumber) {
        ResultSet resultSet = null;
        ConnectionDB.connectSQL();
        String query = "SELECT oState.shop_url, oState.tracking_company_world, oState.trackingnr_world, oState.ship_state, oState.ship_price, oState.box_actual_weight, oState.box_volume_weight, oState.ship_price_discount, paymt.payment_ownername, paymt.payment_state FROM ORDERSTATE oState JOIN PAYMENT paymt ON oState.memberid = ? and oState.orderid = ? and oState.fk_payment = paymt.paymentid";
        //String query = "SELECT * from ORDERSTATE where memberid=? and orderid = ? INNER JOIN PAYMENT paymt ON oState.fk_payment = paymt.paymentid and oState.orderid = ?";
        //ToDo : Jackson
        //select oState.shop_url, oState.ship_state, paymt.payment_state FROM ORDERSTATE oState 
        //JOIN PAYMENT paymt on oState.fk_payment = paymt.paymentid and oState.orderid='20191019145523';                      
        ProductsCommonInformation commonInfo = new ProductsCommonInformation();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(query);){
            psmt.setString(1, username);
            psmt.setString(2, orderNumber);
            resultSet = psmt.executeQuery();
            commonInfo = writeProductsCommonInformation(resultSet, commonInfo);
        } catch (SQLException e) {
            System.out.println(e);  
        }
        
        ResultSet resultSetForPrice = null;
        ConnectionDB.connectSQL();
        String totalPriceQuery = "SELECT pd_totalprice FROM PRODUCT WHERE memberid=? AND orderid=?";
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(totalPriceQuery);){
            psmt.setString(1, username);
            psmt.setString(2, orderNumber);
            resultSetForPrice = psmt.executeQuery();
            commonInfo = writeTotalPriceProductsCommonInformation(resultSetForPrice, commonInfo);
        } catch (SQLException e) {
            //Logger
        }
        return commonInfo;
    }
    
    private static ProductsCommonInformation writeProductsCommonInformation(ResultSet rs, ProductsCommonInformation commonInfo) throws SQLException {
        while (rs.next()) {
            /*refactoring divide table shopurl, tracking..and perform ones*/
            commonInfo.setShipPrice(rs.getInt("ship_price"));
            commonInfo.setPaymentState(rs.getInt("payment_state"));
            commonInfo.setShipState(rs.getInt("ship_state"));
            commonInfo.setShopUrl(rs.getString("shop_url"));
            commonInfo.setTrackingCompany(rs.getString("tracking_company_world"));
            commonInfo.setTrackingNr(rs.getString("trackingnr_world"));
            commonInfo.setActualWeight(rs.getDouble("box_actual_weight"));
            commonInfo.setVolumeWeight(rs.getDouble("box_volume_weight"));
            commonInfo.setPaymentOwnerName(rs.getString("payment_ownername"));
        }
        return commonInfo;
    }
    
    private static ProductsCommonInformation writeTotalPriceProductsCommonInformation(ResultSet rs, ProductsCommonInformation commonInfo) throws SQLException {
        double price=0.0;
        while (rs.next()) {
            price = price + rs.getDouble("pd_totalprice");
        }
        commonInfo.setTotalPrice(price);
        return commonInfo;
    }
    
    public static List<Product> getProductsInfo(String username, String number) {
        ResultSet resultSet = null;
        ConnectionDB.connectSQL();
        String query = "SELECT memberid, orderid, pd_categorytitle"
                + ", pd_itemtitle, pd_itemname, pd_brandname, pd_amount, pd_price, pd_totalprice  FROM PRODUCT WHERE memberid=? AND orderid=?";
        ProductsInformation productsInfo = new ProductsInformation();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(query);){
            psmt.setString(1, username);
            psmt.setString(2, number);
            resultSet = psmt.executeQuery();
            productsInfo = writeProductsInformation(resultSet, productsInfo);
        } catch (SQLException e) {
            //Logger
        }       
        return productsInfo.getProductsList();
    }
    
    private static ProductsInformation writeProductsInformation(ResultSet rs, ProductsInformation products) throws SQLException {
        while (rs.next()) {
            /*refactoring divide table shopurl, tracking..*/
            Product product = products.createProduct();
            product.setCategoryTitle(rs.getString("pd_categorytitle"));
            product.setItemTitle(rs.getString("pd_itemtitle"));
            product.setItemName(rs.getString("pd_itemname"));
            product.setBrandName(rs.getString("pd_brandname"));
            product.setAmount(rs.getInt("pd_amount"));
            product.setPrice(rs.getDouble("pd_price"));
            product.setTotalPrice(rs.getDouble("pd_totalprice"));
            products.getProductsList().add(product);
        }
        return products;
    }
    
    public static ResponseEntity<?> updateRecipientData(String memberId, HashMap<String, Object>[] data) {
        String orderNumber = data[0].get("orderNumber").toString();
        String nameKor = data[1].get("nameKor").toString();
        String nameEng = data[2].get("nameEng").toString();
        String transitNr = data[3].get("transitNr").toString();
        String phonePrefic = data[4].get("phonePrefic").toString();
        String phoneInterfix = data[5].get("phoneInterfix").toString();
        String phoneSuffix = data[6].get("phoneSuffix").toString();
        String zipCode = data[7].get("zipCode").toString();
        String address = data[8].get("address").toString();
        String addressDetails = data[9].get("addressDetails").toString();
        String usercomment = data[10].get("usercomment").toString();
        
        ConnectionDB.connectSQL();
        final String UPDATE_RECIPIENT_DATA = 
                "UPDATE recipient SET name_kor = ?, name_eng = ?, transit_nr = ?, phone_prefic = ?, phone_interfix = ?, phone_suffix = ?, zip_code = ?, address= ?, detail_address = ?, usercomment = ? "
                + "where memberid = ? and orderid = ?";
        
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(UPDATE_RECIPIENT_DATA);){
            psmt.setString(1, nameKor);
            psmt.setString(2, nameEng);
            psmt.setString(3, transitNr);
            psmt.setString(4, phonePrefic);
            psmt.setString(5, phoneInterfix);
            psmt.setString(6, phoneSuffix);
            psmt.setString(7, zipCode);
            psmt.setString(8, address);
            psmt.setString(9, addressDetails);
            psmt.setString(10, usercomment);
            psmt.setString(11, memberId);
            psmt.setString(12, orderNumber);
            
            psmt.executeUpdate();
        } catch (SQLException e) {
            //Logger
        }
        
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
    
    public static ResponseEntity<?> updateDataEditorProductsList(String memberId, HashMap<String, Object>[] data) {
        String orderNumber = data[0].get("orderNumber").toString();
        
        ObjectMapper mapper = new ObjectMapper();
        DeliveryDataObject deliveryDataObj = null;
        ShippingProduct[] shippingProducts = null;
        try {
            deliveryDataObj = mapper.readValue(data[1].get("deliveryDataObject").toString(), DeliveryDataObject.class);
            shippingProducts = mapper.readValue(data[2].get("shippingProductList").toString(), ShippingProduct[].class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        ShippingServiceModel shippingModel = new ShippingServiceModel();
        ShippingServiceDAO shipServiceDao = new ShippingServiceDAO();
        
        shippingModel.setOrderId(orderNumber);
        shippingModel.setMemberId(memberId);
        shippingModel.setDeliveryData(deliveryDataObj);
        shippingModel.setShippingProductsList(shippingProducts);
        
        try {
            shipServiceDao.updateDataShippingServiceDB(shippingModel);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
    
    public static ResponseEntity<?> deleteShipingServiceData(String memberId, HashMap<String, Object>[] data) {
        String orderNumber = data[0].get("orderNumber").toString();
        
        ShippingServiceDAO shipServiceDao = new ShippingServiceDAO();
        try {
            shipServiceDao.deleteShipingServiceData(memberId, orderNumber);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
}

package com.gkoo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import com.gkoo.exception.MypageException;
import databaseUtil.ConnectionDB;

public class MypageDB {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static final String FETTCH_ORDER_DATA = "SELECT os.orderid, os.ship_price, os.ship_state, os.trackingnr_kor, os.trackingnr_world, rp.name_kor "
            + " FROM ORDERSTATE os, RECIPIENT rp WHERE rp.orderid=os.orderid AND os.memberid=?";
    private static final String UPDATE_TRACKNG_NUMBER = "UPDATE orderstate SET trackingnr_world = ?, tracking_company_world = ? where memberid = ? and orderid = ?";
    private static final String FETCH_FAVORITE_ADDRESS_LIST = "SELECT * FROM FAVORITE_ADDRESS WHERE userid=?";
    private static final String CREATE_FAVORITE_ADDRESS = "INSERT INTO favorite_address (userid, name_kor, name_eng, transit_nr, phone_prefic, phone_interfix, phone_suffix, phone_second, zip_code, address, detail_address ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_FAVORITE_ADDRESS = "UPDATE favorite_address SET name_kor=?, name_eng=?, transit_nr=?, phone_prefic=?, phone_interfix=?, phone_suffix=?, phone_second=?, zip_code=?, address=?, detail_address=? where id=?";
    
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
    
    public static List<FavoriteAddress> getFavoriteAddressList(String userid) {
        ConnectionDB.connectSQL();
        List<FavoriteAddress> favoriteAddressList = new ArrayList<>();
        ResultSet resultSet = null;
        
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(FETCH_FAVORITE_ADDRESS_LIST);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            favoriteAddressList = writeFavoriteAddressList(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching favoriteAddressList";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        return favoriteAddressList;
    }
    
    private static List<FavoriteAddress> writeFavoriteAddressList(ResultSet rs) throws SQLException {
        List<FavoriteAddress> favoriteAddressList = new ArrayList<>();
        while (rs.next()) {
            FavoriteAddress favoriteAddress = new FavoriteAddress();
            favoriteAddress.setNameKor(rs.getString("name_kor"));
            favoriteAddress.setNameEng(rs.getString("name_eng"));
            favoriteAddress.setTransitNr(rs.getString("transit_nr"));
            favoriteAddress.setPhonePrefic(rs.getString("phone_prefic"));
            favoriteAddress.setPhoneInterfix(rs.getString("phone_interfix"));
            favoriteAddress.setPhoneSuffix(rs.getString("phone_suffix"));
            favoriteAddress.setPhoneSuffix(rs.getString("phone_second"));
            favoriteAddress.setZipCode(rs.getString("zip_code"));
            favoriteAddress.setAddress(rs.getString("address"));
            favoriteAddress.setAddressDetails(rs.getString("detail_address"));
            favoriteAddressList.add(favoriteAddress);
        }
        return favoriteAddressList;
    }
    
    public static ResponseEntity<?> updateFavoriteAddress(String userid, FavoriteAddress favoriteAddress) {
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(UPDATE_FAVORITE_ADDRESS);){
            psmt.setString(1, favoriteAddress.getNameKor());
            psmt.setString(2, favoriteAddress.getNameEng());
            psmt.setString(3, favoriteAddress.getTransitNr());
            psmt.setString(4, favoriteAddress.getPhonePrefic());
            psmt.setString(5, favoriteAddress.getPhoneInterfix());
            psmt.setString(6, favoriteAddress.getPhoneSuffix());
            psmt.setString(7, favoriteAddress.getPhoneSecond());
            psmt.setString(8, favoriteAddress.getZipCode());
            psmt.setString(9, favoriteAddress.getAddress());
            psmt.setString(10, favoriteAddress.getAddressDetails());
            psmt.setString(11, userid);
            psmt.executeUpdate();
        } catch (SQLException e) {
            String error = "Error updating favorite address";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
    
    public static ResponseEntity<?> createFavoriteAddress(String userid, FavoriteAddress favoriteAddress) {
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(CREATE_FAVORITE_ADDRESS);){
            psmt.setString(1, userid);
            psmt.setString(2, favoriteAddress.getNameKor());
            psmt.setString(3, favoriteAddress.getNameEng());
            psmt.setString(4, favoriteAddress.getTransitNr());
            psmt.setString(5, favoriteAddress.getPhonePrefic());
            psmt.setString(6, favoriteAddress.getPhoneInterfix());
            psmt.setString(7, favoriteAddress.getPhoneSuffix());
            psmt.setString(8, favoriteAddress.getPhoneSecond());
            psmt.setString(9, favoriteAddress.getZipCode());
            psmt.setString(10, favoriteAddress.getAddress());
            psmt.setString(11, favoriteAddress.getAddressDetails());
        } catch (SQLException e) {
            String error = "Error creating favorite address";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
}
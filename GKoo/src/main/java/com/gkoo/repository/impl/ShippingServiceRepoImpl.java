package com.gkoo.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.data.UserBaseInfo;
import com.gkoo.exception.CustomerStatusException;
import com.gkoo.exception.MypageException;
import com.gkoo.repository.ShippingServiceRepository;
import databaseUtil.ConnectionDB;
import shippingService.ShippingProduct;
import shippingService.ShippingServiceModel;
import util.TimeStamp;

@Repository
public class ShippingServiceRepoImpl implements ShippingServiceRepository {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String CREATE_SHIPPING_SERVICE = 
            "insert into recipient(userid, orderid, name_kor, name_eng,"
            + "transit_nr, phonenumber_first, phonenumber_second, zip_code,"
            + "address, userComment ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String CREATE_SHIPPING_PAYMENT = 
            "insert into payment(userid, orderId, payment_state, fk_orderstate) values(?,?,?,?)";
    
    private static final String CREATE_SHIPPING_PRODUCTS = "insert into product(memberId, orderId, order_stamp, "
            + "pd_categorytitle, pd_itemtitle, pd_brandname, pd_itemname, "
            + "pd_amount, pd_price, pd_totalprice) values(?,?,?,?,?,?,?,?,?,?)";
    
    private static final String UPDATE_SHIPPING_ORDER_STATE = 
            "UPDATE ORDERSTATE SET shop_url=?, tracking_company_world=?, trackingnr_world=? WHERE userid=? and orderid=?";
    
    private static final String CREATE_SHIPPING_ORDER_STATE = "insert into orderstate(userid, orderId, ship_price, ship_state, "
            + "tracking_company_world, trackingnr_world, shop_url, order_date) values(?,?,?,?,?,?,?,?) RETURNING orderstate.id";
    
    private static final String FETCH_USERBASEINFO = "select * from customer where userid = ?";

    private static final String REGISTER_FAVORITE_ADDRESS = "INSERT INTO favorite_address (userid, name_kor, name_eng, transit_nr, phonenumber_first, phonenumber_second, zip_code, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

    private static final String DELETE_SHIPPING_SERVICE_PRODUCTS = "DELETE FROM PRODUCT WHERE userid=? and orderid=?";

    private static final String DELETE_SHIPPING_SERVICE_ORDERSTATE = "DELETE FROM ORDERSTATE WHERE userid=? and orderid=?";
    
    private static final String DELETE_SHIPPING_SERVICE_PAYMENT = "DELETE FROM PAYMENT WHERE userid=? and orderid=?";
    
    private static final String DELETE_SHIPPING_SERVICE_RECIPIENT = "DELETE FROM RECIPIENT WHERE userid=? and orderid=?";
    
    public ResponseEntity<?> createShippingService(ShippingServiceModel model) {
        ConnectionDB.connectSQL();      
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(CREATE_SHIPPING_SERVICE);) {
            psmt.setString(1, model.getUserid());
            psmt.setString(2, String.valueOf(model.getOrderId()));
            psmt.setString(3, model.getReceiverNameByKorea());
            psmt.setString(4, model.getReceiverNameByEnglish());
            psmt.setString(5, model.getTransitNumber());
            psmt.setString(6, model.getPhonenumberFirst());
            psmt.setString(7, model.getPhonenumberSecond());
            psmt.setString(8, model.getPostCode());
            psmt.setString(9, model.getDeliveryAddress());
            psmt.setString(10, model.getDeliveryMessage());
    
            psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        ConnectionDB.connectSQL();
        ResultSet resultSet = null;
        int orderstateId = 0;
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(CREATE_SHIPPING_ORDER_STATE);){
            psmt.setString(1, model.getUserid());
            psmt.setString(2, String.valueOf(model.getOrderId()));
            psmt.setDouble(3, model.getShippingPrice());
            psmt.setInt(4, model.getShipState());
            String company = model.getTrackingCompany();
            psmt.setString(5, company == "" ? null : company);
            String trackingNr = model.getTrackingNumber();
            psmt.setString(6, trackingNr == "" ? null : trackingNr);
            String shopUrl = model.getShopUrl();
            psmt.setString(7, shopUrl == "" ? null : shopUrl);
            psmt.setDate(8, model.getOrderDate());
            resultSet  = psmt.executeQuery();
            orderstateId = getOrderStateId(resultSet); 
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(CREATE_SHIPPING_PAYMENT);) {
            psmt.setString(1, model.getUserid());
            psmt.setString(2, String.valueOf(model.getOrderId()));
            psmt.setInt(3, model.getPaymentState());
            psmt.setInt(4, orderstateId);
            psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } 
        
        createShippingProductList(model);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    };
    
    private void createShippingProductList(ShippingServiceModel model) {
        ArrayList<ShippingProduct> products = model.getShippingProductList();
        ConnectionDB.connectSQL();
            try (Connection conn = ConnectionDB.getConnectInstance();
                    PreparedStatement psmt = conn.prepareStatement(CREATE_SHIPPING_PRODUCTS);) {
                
                for(int i=0; i< products.size(); i++) {
                    psmt.setString(1, model.getUserid());
                    psmt.setString(2, String.valueOf(model.getOrderId()));
                    psmt.setTimestamp(3, TimeStamp.getTimestampKorea());
                    psmt.setString(4, products.get(i).getCategoryTitle());
                    psmt.setString(5, products.get(i).getItemTitle());
                    psmt.setString(6, products.get(i).getBrandName());
                    psmt.setString(7, products.get(i).getItemName());
                    psmt.setInt(8, products.get(i).getProductAmount());
                    psmt.setDouble(9, products.get(i).getProductPrice());
                    psmt.setDouble(10, products.get(i).getProductTotalPrice());
                        
                    psmt.executeUpdate();
                }
            } catch (SQLException ex) {
                LOGGER.error("Creating ShippingServiceProduct is failed", ex);
            }
    }
    
    private int getPaymentId(ResultSet rs) throws SQLException {
        int paymentid=0;
        while (rs.next()) {
            paymentid = rs.getInt("paymentid");
        }
        return paymentid;
    }
    
    private int getOrderStateId(ResultSet rs) throws SQLException {
        int orderstateID=0;
        while (rs.next()) {
            orderstateID = rs.getInt("id");
        }
        return orderstateID;
    }

    @Override
    public UserBaseInfo getUserBaseInfo(String userid) {
        ConnectionDB.connectSQL();
        ResultSet resultSet = null;
        UserBaseInfo userBaseInfo = null;
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(FETCH_USERBASEINFO);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            userBaseInfo = writeUserBaseInfo(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching userBaseInfo";
            LOGGER.error(error, e);
            throw new CustomerStatusException(error, e);
        }
        return userBaseInfo;
    }
    
    private static UserBaseInfo writeUserBaseInfo(ResultSet resultSet) throws SQLException {
        UserBaseInfo userBaseInfo =  new UserBaseInfo();
        while (resultSet.next()) {
            userBaseInfo.setUserid(resultSet.getString("userid"))
                         .withNameKor(resultSet.getString("name_kor"))
                         .withNameEng(resultSet.getString("name_eng"))
                         .withEmail(resultSet.getString("email"))
                         .withTransitNr(resultSet.getString("transit_nr"))
                         .withPhonenumberFirst(resultSet.getString("phonenumber_first"))
                         .withPhonenumberSecond(resultSet.getString("phonenumber_second"))
                         .withZipCode(resultSet.getString("zip_code"))
                         .withAddress(resultSet.getString("address"));
        }
        return userBaseInfo;
    }

    @Override
    public ResponseEntity<?> registerFavoriteAddress(FavoriteAddress favoriteAddress, String userid) {
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(REGISTER_FAVORITE_ADDRESS);){
            psmt.setString(1, userid);
            psmt.setString(2, favoriteAddress.getNameKor());
            psmt.setString(3, favoriteAddress.getNameEng());
            psmt.setString(4, favoriteAddress.getTransitNr());
            psmt.setString(5, favoriteAddress.getPhonenumberFirst());
            psmt.setString(6, favoriteAddress.getPhonenumberSecond());
            psmt.setString(7, favoriteAddress.getZipCode());
            psmt.setString(8, favoriteAddress.getAddress());
            psmt.executeUpdate();
        } catch (SQLException e) {
            String error = "Error creating favorite address";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> updateDataShippingService(ShippingServiceModel model) {
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(UPDATE_SHIPPING_ORDER_STATE);
                PreparedStatement psmt_delete = conn.prepareStatement(DELETE_SHIPPING_SERVICE_PRODUCTS);){
            psmt.setString(1, model.getShopUrl());
            psmt.setString(2, model.getTrackingCompany());
            psmt.setString(3, model.getTrackingNumber());
            psmt.setString(2, model.getTrackingCompany());
            psmt.setString(4, model.getUserid());
            psmt.setString(5, model.getOrderId());
            
            psmt_delete.setString(1, model.getUserid());
            psmt_delete.setString(2, model.getOrderId());
            
            psmt.executeUpdate();
            psmt_delete.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex); 
        } 
        
        createShippingProductList(model);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> deleteShipingServiceData(String userid, String orderNumber) {
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt_orderstate = conn.prepareStatement(DELETE_SHIPPING_SERVICE_ORDERSTATE);
                PreparedStatement psmt_payment = conn.prepareStatement(DELETE_SHIPPING_SERVICE_PAYMENT);
                PreparedStatement psmt_recipient = conn.prepareStatement(DELETE_SHIPPING_SERVICE_RECIPIENT);
                PreparedStatement psmt_products = conn.prepareStatement(DELETE_SHIPPING_SERVICE_PRODUCTS);                
                ) {
            ArrayList<PreparedStatement> psmt_list = new ArrayList<>();
            psmt_list.add(psmt_orderstate);
            psmt_list.add(psmt_payment);
            psmt_list.add(psmt_recipient);
            psmt_list.add(psmt_products);
            for(PreparedStatement psmt : psmt_list) {
                psmt.setString(1, userid);
                psmt.setString(2, orderNumber);
                psmt.executeUpdate();
            }
        } catch (SQLException ex) {
            //ShippingServiceDAOException 
            System.out.println(ex);
            LOGGER.error("ShipingServiceData har error." + "id: "+ userid + "odernumber: " + orderNumber, ex);
        }
        LOGGER.info("ShipingServiceData is deleted." + "id: "+ userid + "odernumber: " + orderNumber);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
}
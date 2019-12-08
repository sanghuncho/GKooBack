package com.gkoo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.CustomerStatus;
import com.gkoo.data.UserBaseInfo;
import com.gkoo.exception.CustomerStatusException;
import com.google.common.util.concurrent.CycleDetectingLockFactory.WithExplicitOrdering;
import databaseUtil.ConnectionDB;

public class CustomerStatusDB {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static final int INITIAL_POINT = 1000;
    private static final String NUMBER_USERID = "select count(gkoo_id) from customer where gkoo_id = ?";
    private static final String CREATE_CUSTOMER_STATUS = "INSERT INTO customerstatus(gkoo_id, insuranceamount, depositeamount, pointamount)"
            + "VALUES (?, 0, 0, ?)";
    private static final String CREATE_CUSTOMER = "INSERT INTO customer(gkoo_id, lastname, firstname)"
            + "VALUES (?, ?, ?)";
    private static final String FETCH_CUSTOMERSTATUS = "select * from customerstatus where gkoo_id = ?";
    private static final String FETCH_USERBASEINFO = "select * from customer where gkoo_id = ?";
    private static final String UPDATE_USERBASEINFO = "UPDATE CUSTOMER SET firstname=?, lastname=?, address=?, email=?, name_eng=?, detail_address=?, "
            + "transit_nr=?, zip_code=?, phone_prefic=?, phone_interfix=?, phone_suffix=? where gkoo_id=?";
    
    public static Boolean existUserid(String userid) throws SQLException {
        ResultSet resultSet = null;
        ConnectionDB.connectSQL();
        int number = 0;
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(NUMBER_USERID);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            while(resultSet.next()) {
                number = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            String error = "Error retriving the numer of userid";
            LOGGER.error(error, e);
            throw new CustomerStatusException(error, e);
        }
        return number == 0 ? false : true;
    }
    
    public static void registerInitialCustomer(String userid, String lastname, String firstname) {
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt_customerstatus = conn.prepareStatement(CREATE_CUSTOMER_STATUS);
                PreparedStatement psmt_customer = conn.prepareStatement(CREATE_CUSTOMER);){
            psmt_customerstatus.setString(1, userid);
            psmt_customerstatus.setInt(2, INITIAL_POINT);
            psmt_customerstatus.execute();
            
            psmt_customer.setString(1, userid);
            psmt_customer.setString(2, lastname);
            psmt_customer.setString(3, firstname);
            psmt_customer.execute();
        } catch (SQLException e) {
            String error = "Error creating initial customerstatus and customer";
            LOGGER.error(error, e);
            throw new CustomerStatusException(error, e);
        }
    }
    
    public static CustomerStatus getCustomerStatus(String userid) {
        ConnectionDB.connectSQL();
        CustomerStatus customerStatus = null;
        ResultSet resultSet = null;
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(FETCH_CUSTOMERSTATUS);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            customerStatus = writeCustomerStatus(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching customerstatus";
            LOGGER.error(error, e);
            throw new CustomerStatusException(error, e);
        }
        return customerStatus;
    }
    
    private static CustomerStatus writeCustomerStatus(ResultSet resultSet) throws SQLException {
        CustomerStatus customerStatus =  new CustomerStatus();
        while (resultSet.next()) {
            customerStatus.setUserid(resultSet.getString("gkoo_id"));
            customerStatus.setInsuranceAmount(resultSet.getInt("insuranceamount"));
            customerStatus.setDepositeAmount(resultSet.getInt("depositeamount"));
            customerStatus.setPointAmount(resultSet.getInt("pointAmount"));
        }
        return customerStatus;
    }
    
    public static UserBaseInfo getUserBaseInfo(String userid) {
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
            userBaseInfo.setPhoneSuffix(resultSet.getString("phone_suffix"));
            userBaseInfo.setUserid(resultSet.getString("gkoo_id"))
                         .withFirstName(resultSet.getString("firstname"))
                         .withLastName(resultSet.getString("lastname"))
                         .withAddress(resultSet.getString("address"))
                         .withEmail(resultSet.getString("email"))
                         .withNameEng(resultSet.getString("name_eng"))
                         .withDetailAddress(resultSet.getString("detail_address"))
                         .withTransitNr(resultSet.getString("transit_nr"))
                         .withZipCode(resultSet.getString("zip_code"))
                         .withPhonePrefic(resultSet.getString("phone_prefic"))
                         .withPhoneInterfix(resultSet.getString("phone_interfix"))
                         .withPhoneSuffix(resultSet.getString("phone_suffix"));
        }
        return userBaseInfo;
    }
    
    public static ResponseEntity<?> updateBaseInfo(UserBaseInfo data, String userid){
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(UPDATE_USERBASEINFO);){
            psmt.setString(1, data.getFirstName());
            psmt.setString(2, data.getLastName());
            psmt.setString(3, data.getAddress());
            psmt.setString(4, data.getEmail());
            psmt.setString(5, data.getNameEng());
            psmt.setString(6, data.getDetailAddress());
            psmt.setString(7, data.getTransitNr());
            psmt.setString(8, data.getZipCode());
            psmt.setString(9, data.getPhonePrefic());
            psmt.setString(10, data.getPhoneInterfix());
            psmt.setString(11, data.getPhoneSuffix());
            psmt.setString(12, userid);
            psmt.executeUpdate();
        } catch (SQLException ex) {
            String error = "Error updating userBaseInfo";
            LOGGER.error(error, ex);
            throw new CustomerStatusException(error, ex);
        } 
        String responseMessage = "userBaseInfoData is updateded for userid:" + userid;
        return new ResponseEntity<String>(responseMessage, HttpStatus.ACCEPTED);
    }
}
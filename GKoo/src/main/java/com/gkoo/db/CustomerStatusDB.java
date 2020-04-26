package com.gkoo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.ConfigurationData;
import com.gkoo.data.CustomerStatus;
import com.gkoo.data.UserBaseInfo;
import com.gkoo.exception.CustomerStatusException;
import databaseUtil.ConnectionDB;

public class CustomerStatusDB {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static final int INITIAL_POINT = 1000;
    private static final String NUMBER_USERID = "select count(userid) from customer where userid = ?";
    private static final String CREATE_CUSTOMER_STATUS = "INSERT INTO customerstatus(gkoo_id, insuranceamount, depositeamount, pointamount)"
            + "VALUES (?, 0, 0, ?)";
    private static final String CREATE_CUSTOMER = "INSERT INTO customer(userid, name_kor, email, personal_box_address)"
            + "VALUES (?, ?, ?, ?)";
    //private static final String FETCH_CUSTOMERSTATUS = "select * from customerstatus where gkoo_id = ?";
    
    private static final String FETCH_CUSTOMERSTATUS = "SELECT status.gkoo_id, status.insuranceamount, status.depositeamount, status.pointamount, customer.personal_box_address "
            + "                                             FROM customerstatus status INNER JOIN customer customer on status.gkoo_id = customer.userid and gkoo_id = ?";
    private static final String FETCH_USERBASEINFO = "select * from customer where userid = ?";
    private static final String UPDATE_USERBASEINFO = "UPDATE CUSTOMER SET name_kor=?, name_eng=?, email=?, transit_nr=?, phonenumber_first=?, phonenumber_second=?, "
            + "zip_code=?, address=? where userid=?";
    private static final String FETCH_PERSONAL_BOX_ADDRESS = "SELECT last_personalboxaddress from CONFIGURATION;";
    private static final String UPDATE_PERSONAL_BOX_ADDRESS = "UPDATE CONFIGURATION SET last_personalboxaddress = ?";

    
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
    
    public static int getPersonaBoxAddress() {
        ResultSet resultSet = null;
        ConnectionDB.connectSQL();
        int personalBoxNumber = 0;
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(FETCH_PERSONAL_BOX_ADDRESS);){
            resultSet = psmt.executeQuery();
            while(resultSet.next()) {
                personalBoxNumber = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            String error = "Error fetching the personal box Address";
            LOGGER.error(error, e);
            throw new CustomerStatusException(error, e);
        }
        return personalBoxNumber;
    }
    
    public static void registerInitialCustomer(String userid, String fullnameKor, String email, String personalBoxAddressStr, int lastPersonalBoxAddress) {
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt_customerstatus = conn.prepareStatement(CREATE_CUSTOMER_STATUS);
                PreparedStatement psmt_customer = conn.prepareStatement(CREATE_CUSTOMER);
                PreparedStatement psmt_update_personal_boxaddress = conn.prepareStatement(UPDATE_PERSONAL_BOX_ADDRESS);){
            psmt_customerstatus.setString(1, userid);
            psmt_customerstatus.setInt(2, INITIAL_POINT);
            psmt_customerstatus.execute();
            
            psmt_customer.setString(1, userid);
            psmt_customer.setString(2, fullnameKor);
            psmt_customer.setString(3, email);
            psmt_customer.setString(4, personalBoxAddressStr);
            psmt_customer.execute();
            
            try {
                psmt_update_personal_boxaddress.setInt(1, lastPersonalBoxAddress);
                psmt_update_personal_boxaddress.execute();
            } catch (SQLException e) {
                String error = "Error updating the personal box Address: " + lastPersonalBoxAddress;
                LOGGER.error(error, e);
                throw new CustomerStatusException(error, e);
            }
        } catch (SQLException e) {
            String error = "Error creating initial customerstatus, customer: " + userid + "/" + fullnameKor + "/" + personalBoxAddressStr;
            LOGGER.error(error, e);
            throw new CustomerStatusException(error, e);
        }
    }
    
    //@deprecated customerstatus merging to customer!!
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
            customerStatus.setPersonalBoxAddress(resultSet.getString("personal_box_address"));
        }
        return customerStatus;
    }
    
    public static UserBaseInfo getUserBaseInfo(String userid) {
        ResultSet resultSet = null;
        UserBaseInfo userBaseInfo = null;
        
        ConnectionDB.connectSQL();
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
    
    public static ResponseEntity<?> updateBaseInfo(UserBaseInfo data, String userid){
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(UPDATE_USERBASEINFO);){
            psmt.setString(1, data.getNameKor());
            psmt.setString(2, data.getNameEng());
            psmt.setString(3, data.getEmail());
            psmt.setString(4, data.getTransitNr());
            psmt.setString(5, data.getPhonenumberFirst());
            psmt.setString(6, data.getPhonenumberSecond());
            psmt.setString(7, data.getZipCode());
            psmt.setString(8, data.getAddress());
            psmt.setString(9, userid);
            psmt.executeUpdate();
        } catch (SQLException ex) {
            String error = "Error updating userBaseInfo";
            LOGGER.error(error, data, ex);
            throw new CustomerStatusException(error, ex);
        } 
        String responseMessage = "userBaseInfoData is updateded for userid:" + userid;
        return new ResponseEntity<String>(responseMessage, HttpStatus.ACCEPTED);
    }
}
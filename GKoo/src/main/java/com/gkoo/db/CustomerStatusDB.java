package com.gkoo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.gkoo.data.CustomerStatus;
import com.gkoo.exception.CustomerStatusException;
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
    
    public static Boolean existUserid(String userid) throws SQLException {
        ResultSet resultSet = null;
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(NUMBER_USERID);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
        } catch (SQLException e) {
            String error = "Error retriving the numer of userid";
            LOGGER.error(error, e);
            throw new CustomerStatusException(error, e);
        }
        return resultSet.getInt(1) == 0 ? false : true;
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
        } catch (SQLException e) {
            String error = "Error fetching customerstatus";
            LOGGER.error(error, e);
            throw new CustomerStatusException(error, e);
        }
        
        try {
            customerStatus = writeCustomerStatus(resultSet);
        } catch (SQLException e) {
            String error = "Error writing customerstatus";
            LOGGER.error(error, e);
            throw new CustomerStatusException(error, e);
        }
        return customerStatus;
    }
    
    private static CustomerStatus writeCustomerStatus(ResultSet resultSet) throws SQLException {
        CustomerStatus customerStatus =  new CustomerStatus();
        while (resultSet.next()) {
            customerStatus.setCustomerId(resultSet.getString("gkoo_id"));
            customerStatus.setInsuranceAmount(resultSet.getInt("insuranceamount"));
            customerStatus.setDepositeAmount(resultSet.getInt("depositeamount"));
            customerStatus.setPointAmount(resultSet.getInt("pointAmount"));
        }
        return customerStatus;
    }
}
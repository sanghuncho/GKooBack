package mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.keycloak.representations.AccessToken;

import databaseUtil.ConnectionDB;

public class CustomerStatusDAO {
	
	private Statement statement = null;
    private ResultSet resultSet = null;
    private Connection conn;
    
	public CustomerStatusDAO(){}
	
	public CustomerStatus getCustomerStatus() {
		
		/**
	     * initial state
	     */
		CustomerStatus customer = new CustomerStatus(
				"gkoo 10001",
				1000,
				1000,
				1000);
		
		return customer;
	}
	
	public void checkGkooId(AccessToken accessToken) throws SQLException {
		resultSet = null;
		ConnectionDB.connectSQL();
		Connection conn = ConnectionDB.getConnectInstance();
		
		/*
		 * keycloak- config null object username, lastname, firstname*/
		String username = accessToken.getPreferredUsername();
		String lastname = accessToken.getFamilyName();
        String firstname = accessToken.getGivenName();
        
		String query = "select count(gkoo_id) from customer where gkoo_id = ?";
		PreparedStatement psmt = conn.prepareStatement(query);
		psmt.setString(1, username);
		
		try {
			resultSet = psmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		while(resultSet.next()) {
			if(resultSet.getInt(1) == 0) {
				System.out.println("It needs to register");
				/**
			     *ToDo: email should be also stored in DB.
			     */
				registerGkooIdInDB(username, lastname, firstname);
				
			} else {
				System.out.println("already exist!!");
			}	
		}
		close();
	}
	
	public void registerGkooIdInDB(String username, String lastname, String firstname) throws SQLException {
		ConnectionDB.connectSQL();
		Connection conn = ConnectionDB.getConnectInstance();
		
		/**
	     *ToDo: customer needs to also register.
	     */
		String queryStatus = "INSERT INTO customerstatus(gkoo_id, insuranceamount, depositeamount, pointamount)"
				+ "VALUES (?, 0, 0, 1000)";
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(queryStatus);
			psmt.setString(1, username);
			psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		String queryCustomer = "INSERT INTO customer(gkoo_id, lastname, firstname)"
				+ "VALUES (?, ?, ?)";
		PreparedStatement psmt_customer = null;
		try {
			psmt_customer = conn.prepareStatement(queryCustomer);
			psmt_customer.setString(1, username);
			psmt_customer.setString(2, lastname);
			psmt_customer.setString(3, firstname);
			psmt_customer.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public CustomerStatus getCustomerStatusFromDB(String username) throws SQLException{
		resultSet = null;
		ConnectionDB.connectSQL();
		Connection conn = ConnectionDB.getConnectInstance();
		String query = "select * from customerstatus where gkoo_id = ?";
		PreparedStatement psmt = null;
				
		CustomerStatus customerStatus = null;
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, username);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		try {
			resultSet = psmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			customerStatus = writeCustomerStatusContent(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return customerStatus;
	}
	
	private CustomerStatus writeCustomerStatusContent(ResultSet rs) throws SQLException {
		CustomerStatus status =  new CustomerStatus();
		
		while (rs.next()) {
			
			status.setCustomerId(rs.getString("gkoo_id"));
			
			status.setInsuranceAmount(rs.getInt("insuranceamount"));
			
			status.setDepositeAmount(rs.getInt("depositeamount"));
			
			status.setPointAmount(rs.getInt("pointAmount"));
			
		}
        return status;
    }
	
	private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
    }
	
}

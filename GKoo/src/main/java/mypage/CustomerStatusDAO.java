package mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataBase.ConnectionDB;

public class CustomerStatusDAO {
	
	private Statement statement = null;
    private ResultSet resultSet = null;
    private Connection conn;
    
	public CustomerStatusDAO(){}
	
	public CustomerStatus getCustomerStatus() {
		
		CustomerStatus customer = new CustomerStatus(
				"gkoo 10001",
				1000,
				1000,
				1000);
		
		return customer;
	}
	
	public CustomerStatus getCustomerStatusFromDB() throws SQLException{
		ConnectionDB.connectSQL();
		Connection conn = ConnectionDB.getConnectInstance();
		
		CustomerStatus customerStatus = null;
		try {
			this.statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			resultSet = this.statement.executeQuery("select * from customerstatus");
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
			
			status.setCustomerId(rs.getString("gkooaddress"));
			
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

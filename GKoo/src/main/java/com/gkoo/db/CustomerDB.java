package com.gkoo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.gkoo.data.CustomerData;
import com.gkoo.exception.CustomerException;
import databaseUtil.ConnectionDB;

public class CustomerDB {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static final String GET_CUSTOMER_NAME_KOR= "select name_kor from CUSTOMER where userid = ?";
    
    public static CustomerData getCustomerData(String userid) {
        ResultSet resultSet = null;
        CustomerData customerData = new CustomerData();

        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(GET_CUSTOMER_NAME_KOR);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            customerData = writeCustomerName(resultSet, customerData);
        } catch (SQLException e) {
            String error = "Error fetching name of customer";
            LOGGER.error(error, e);
            throw new CustomerException(error, e);
        }
        return customerData;
    }
    
    private static CustomerData writeCustomerName(ResultSet rs, CustomerData customerData) throws SQLException {
        while (rs.next()) {
            String nameKor = rs.getString("name_kor");
            customerData.setNameKor(nameKor);
        }
        return customerData;
    }
}
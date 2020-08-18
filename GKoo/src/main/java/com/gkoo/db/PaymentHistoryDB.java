package com.gkoo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.gkoo.data.PaymentHistoryData;
import com.gkoo.exception.CustomerException;
import databaseUtil.ConnectionDB;

/**
 * @author sanghuncho
 *
 */
public class PaymentHistoryDB {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String GET_PAYMENT_HISTORY_TRANSFER= "select * from PAYMENT_HISTORY_TRANSFER where userid = ?";
    private static final String GET_PAYMENT_HISTORY_DEPOSIT= "select * from PAYMENT_HISTORY_DEPOSIT where userid = ?";

    public static PaymentHistoryData getPaymentHistoryTransfer(String userid) {
        ResultSet resultSet = null;
        PaymentHistoryData paymentHistoryData = new PaymentHistoryData();

        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(GET_PAYMENT_HISTORY_TRANSFER);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            paymentHistoryData = writePaymentHistoryTransfer(resultSet, paymentHistoryData);
        } catch (SQLException e) {
            String error = "Error fetching paymentHistoryTransfer";
            LOGGER.error(error, e);
            throw new CustomerException(error, e);
        }
        return paymentHistoryData;
    }
    
    private static PaymentHistoryData writePaymentHistoryTransfer(ResultSet rs, PaymentHistoryData paymentHistoryData) throws SQLException {
        while (rs.next()) {
            paymentHistoryData.setPaymentDate(rs.getDate("payment_date"));
            paymentHistoryData.setDepositPayment(rs.getDouble("transfer_deposit_payment"));
            paymentHistoryData.setBuyingPayment(rs.getDouble("buying_payment"));
            paymentHistoryData.setShippingPayment(rs.getDouble("shipping_payment"));
            paymentHistoryData.setOrderid(rs.getString("orderid"));
            paymentHistoryData.setItemname(rs.getString("pd_itemname"));
        }
        return paymentHistoryData;
    }
    
    public static PaymentHistoryData getPaymentHistoryDeposit(String userid) {
        ResultSet resultSet = null;
        PaymentHistoryData paymentHistoryData = new PaymentHistoryData();

        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(GET_PAYMENT_HISTORY_DEPOSIT);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            paymentHistoryData = writePaymentHistoryDeposit(resultSet, paymentHistoryData);
        } catch (SQLException e) {
            String error = "Error fetching paymentHistoryDeposit";
            LOGGER.error(error, e);
            throw new CustomerException(error, e);
        }
        return paymentHistoryData;
    }
    
    private static PaymentHistoryData writePaymentHistoryDeposit(ResultSet rs, PaymentHistoryData paymentHistoryData) throws SQLException {
        while (rs.next()) {
            paymentHistoryData.setPaymentDate(rs.getDate("payment_date"));
            paymentHistoryData.setDepositPayment(rs.getDouble("deposit_payment"));
            paymentHistoryData.setDepositPayment(rs.getDouble("being_deposit"));
            paymentHistoryData.setDepositPayment(rs.getDouble("actual_deposit"));
            paymentHistoryData.setBuyingPayment(rs.getDouble("buying_payment"));
            paymentHistoryData.setShippingPayment(rs.getDouble("shipping_payment"));
            paymentHistoryData.setOrderid(rs.getString("orderid"));
            paymentHistoryData.setItemname(rs.getString("pd_itemname"));
        }
        return paymentHistoryData;
    }
}

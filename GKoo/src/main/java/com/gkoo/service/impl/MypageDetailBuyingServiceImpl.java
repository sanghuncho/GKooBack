package com.gkoo.service.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gkoo.service.MypageDetailBuyingService;
import databaseUtil.ConnectionDB;

@Service
public class MypageDetailBuyingServiceImpl implements MypageDetailBuyingService {
    private static final Logger LOGGER = LogManager.getLogger();
    
    @Override
    public ResponseEntity<?> deleteBuyingServiceData(String orderid) throws JsonParseException, JsonMappingException, IOException, SQLException {

        final String DELETE_BUYING_SERVICE_PAYMENT = "DELETE FROM BUYING_SERVICE_PAYMENT "
                + "USING BUYING_SERVICE WHERE BUYING_SERVICE_PAYMENT.fk_buying_service = BUYING_SERVICE.object_id AND BUYING_SERVICE.orderid = ?";
        final String DELETE_BUYING_SERVICE_PRODUCT = "DELETE FROM BUYING_SERVICE_PRODUCT "
                + "USING BUYING_SERVICE WHERE BUYING_SERVICE_PRODUCT.fk_buying_service = BUYING_SERVICE.object_id AND BUYING_SERVICE.orderid = ?";
        final String DELETE_BUYING_SERVICE_RECIPIENT = "DELETE FROM BUYING_SERVICE_RECIPIENT "
                + "USING BUYING_SERVICE WHERE BUYING_SERVICE_RECIPIENT.fk_buying_service = BUYING_SERVICE.object_id AND BUYING_SERVICE.orderid = ?";
        final String DELETE_BUYING_SERVICE = "DELETE FROM BUYING_SERVICE WHERE orderid = ?";
        
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt_payment = conn.prepareStatement(DELETE_BUYING_SERVICE_PAYMENT);
                PreparedStatement psmt_product = conn.prepareStatement(DELETE_BUYING_SERVICE_PRODUCT);
                PreparedStatement psmt_recipient = conn.prepareStatement(DELETE_BUYING_SERVICE_RECIPIENT);
                PreparedStatement psmt_buyingservice = conn.prepareStatement(DELETE_BUYING_SERVICE);                
                ) {
            ArrayList<PreparedStatement> psmt_list = new ArrayList<>();
            psmt_list.add(psmt_payment);
            psmt_list.add(psmt_product);
            psmt_list.add(psmt_recipient);
            psmt_list.add(psmt_buyingservice);
            for(PreparedStatement psmt : psmt_list) {
                psmt.setString(1, orderid);
                psmt.executeUpdate();
            }
            
        } catch (SQLException ex) {
            LOGGER.error("Error deleting BuyingService : orderid: " + orderid, ex);
        }
        LOGGER.info("BuyingService is deleted - orderid: " + orderid);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
}

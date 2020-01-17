package com.gkoo.service.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkoo.data.ConfigurationData;
import com.gkoo.data.EstimationService;
import com.gkoo.data.buyingservice.BuyingProduct;
import com.gkoo.data.buyingservice.BuyingServiceData;
import com.gkoo.enums.BuyingServicePaymentState;
import com.gkoo.enums.BuyingServiceState;
import com.gkoo.service.BuyingService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import databaseUtil.ConnectionDB;
import shippingService.ShippingProduct;
import shippingService.ShippingServiceModel;
import util.OrderID;
import util.TimeStamp;

@Service
public class BuyingServiceImpl implements BuyingService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String currencyServiceUrl = "https://api.exchangeratesapi.io/latest?base=EUR";
    private final double INITIAL_SHIP_PRICE = 0;
    private static final String CREATE_BUYING_SERVICE = 
            "insert into buying_service(id, userid, orderid, buying_price, payment_state, ship_state, shop_url, address, userComment ) values (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING buying_service.id";
    
    private static final String CREATE_BUYING_SERVICE_PAYMENT =  "insert into buying_service_payment(userid, orderid, buying_service_payment_state, fk_buying_service) values(?,?,?,?)";
    
    private static final String CREATE_BUYING_PRODUCT = "insert into product(userid, orderid, pd_categorytitle, pd_itemtitle, pd_brandname, pd_itemname, "
            + "pd_amount, pd_price, pd_totalprice) values(?,?,?,?,?,?,?,?,?)";
    
    @Override
    public EstimationService estimateBuyingService(HashMap<String, Object>[] data, String userid) {
        double totalPrice = 15.9;
        //options
        boolean mergeBox = false;
        boolean inputDeliveryFee = false;
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
          = restTemplate.getForEntity(currencyServiceUrl, String.class);
        
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject)parser.parse(response.getBody());
        System.out.println(response.getBody());
        JsonElement jsonElement = jsonObject.get("rates");
        
        JsonObject rates = jsonElement.getAsJsonObject();
        System.out.println(rates.get("KRW").getAsDouble());
        
        double currentEurToKRW = rates.get("KRW").getAsDouble();
        EstimationService estimation = new EstimationService();
        estimation.setResultPrice(getEstimationBuyingService(currentEurToKRW, totalPrice, mergeBox));
        estimation.setInputDeliveryFee(inputDeliveryFee);
        
        return estimation;
    }
    
    public double getEstimationBuyingService(double currentEurToKRW, double totalPrice, boolean mergeBox) {
        double feePercent = ConfigurationData.BUYING_SERVICE_FEE_PERCENT;
        double result = (currentEurToKRW*totalPrice)*(1 + feePercent);
        if (mergeBox) {
            double mergingBoxFee = ConfigurationData.MERGING_BOX_FEE;
            result = result + mergingBoxFee;
        }
        return result;
    }
    
    public ResponseEntity<?> createBuyingService(@RequestBody HashMap<String, Object>[] data, String userid){
        String orderid = OrderID.generateOrderID();
        LocalDate orderDate = TimeStamp.getRequestDate();
        
        LOGGER.info("구매대행 서비스 신청: "+ userid + "/배송대행 서비스주문번호: " + orderid);
        
        BuyingServiceData buyingServiceData = new BuyingServiceData();
        buyingServiceData.setUserid(userid);
        buyingServiceData.setOrderid(orderid);
        buyingServiceData.setOrderDate(orderDate);
        
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            buyingServiceData = mapper.readValue(data[1].get("buyingServiceObject").toString(), BuyingServiceData.class);
        } catch (IOException e) {
            LOGGER.error("Mapping of deliveryDataObject is failed:" + userid + "/" + orderid, e);
        }
        
        buyingServiceData.setShippingPrice(INITIAL_SHIP_PRICE);
        buyingServiceData.setBuyingState(BuyingServiceState.PRODUCT_PAYMENT_READY);
        buyingServiceData.setBuyingServicePaymentState(BuyingServicePaymentState.PRODUCT_PAYMENT_READY);
        
        return createBuyingService(buyingServiceData);
    }
    
   
    private  ResponseEntity<?> createBuyingService(BuyingServiceData buyingservicedata){
        ConnectionDB.connectSQL();
        ResultSet resultSet = null;
        int buyingServiceId = 0;
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(CREATE_BUYING_SERVICE);) {
            psmt.setString(1, buyingservicedata.getUserid());
            psmt.setString(2, buyingservicedata.getOrderid());
            psmt.setDouble(3, buyingservicedata.getBuyingPrice());
            psmt.setInt(4, buyingservicedata.getBuyingServicePaymentState().getCode());
            psmt.setInt(5, buyingservicedata.getBuyingState().getCode());
            psmt.setString(6, buyingservicedata.getShopUrl());
            psmt.setString(7, buyingservicedata.getDeliveryAddress());
            psmt.setString(8, buyingservicedata.getDeliveryMessage());
            resultSet  = psmt.executeQuery();
            buyingServiceId = getBuyingServiceId(resultSet);
            buyingservicedata.setBuyingServiceid(buyingServiceId);
        } catch (SQLException e) {
              LOGGER.error("Creating BuyingService is failed", e);
        }
        
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(CREATE_BUYING_SERVICE_PAYMENT);) {
            psmt.setString(1, buyingservicedata.getUserid());
            psmt.setString(2, buyingservicedata.getOrderid());
            psmt.setInt(3, buyingservicedata.getBuyingServicePaymentState().getCode());
            psmt.setInt(4, buyingservicedata.getBuyingServiceid());
            psmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Creating BuyingServicePayment is failed", e);
        } 
        
        createBuyingProductList(buyingservicedata);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    private void createBuyingProductList(BuyingServiceData buyingServiceData) {
        ArrayList<BuyingProduct> products = buyingServiceData.getBuyingProductList();
        ConnectionDB.connectSQL();
            try (Connection conn = ConnectionDB.getConnectInstance();
                    PreparedStatement psmt = conn.prepareStatement(CREATE_BUYING_PRODUCT);) {
                
                for(int i=0; i< products.size(); i++) {
                    psmt.setString(1, buyingServiceData.getUserid());
                    psmt.setString(2, buyingServiceData.getOrderid());
                    psmt.setString(3, products.get(i).getCategoryTitle());
                    psmt.setString(4, products.get(i).getItemTitle());
                    psmt.setString(5, products.get(i).getBrandName());
                    psmt.setString(6, products.get(i).getItemName());
                    psmt.setInt(7, products.get(i).getProductAmount());
                    psmt.setDouble(8, products.get(i).getProductPrice());
                    psmt.setDouble(9, products.get(i).getProductTotalPrice());
                        
                    psmt.executeUpdate();
                }
            } catch (SQLException ex) {
                LOGGER.error("Creating BuyingServiceProducts is failed", ex);
            }
    }
    
    private int getBuyingServiceId(ResultSet rs) throws SQLException {
        int buyingServiceId = 0;
        while (rs.next()) {
            buyingServiceId = rs.getInt("id");
        }
        return buyingServiceId;
    }
}
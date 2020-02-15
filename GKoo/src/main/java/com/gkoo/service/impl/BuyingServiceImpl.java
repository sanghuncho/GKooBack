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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkoo.data.ConfigurationData;
import com.gkoo.data.EstimationService;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.data.RecipientData;
import com.gkoo.data.UserBaseInfo;
import com.gkoo.data.buyingservice.BuyingProduct;
import com.gkoo.data.buyingservice.BuyingServiceData;
import com.gkoo.db.AddressManagerDB;
import com.gkoo.db.CustomerStatusDB;
import com.gkoo.enums.BuyingServicePaymentState;
import com.gkoo.enums.BuyingServiceState;
import com.gkoo.service.BuyingService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import databaseUtil.ConnectionDB;
import util.OrderID;
import util.TimeStamp;
import org.springframework.http.HttpStatus;


@Service
public class BuyingServiceImpl implements BuyingService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String CURRENTCY_SERVICE_URL = "https://api.exchangeratesapi.io/latest?base=EUR";
    private final double INITIAL_SHIP_PRICE = 0;
    
    private static final String CREATE_BUYING_SERVICE = 
            "insert into buying_service(userid, orderid, buying_price,   buying_service_state, shop_url, order_date ) values (?, ?, ?,   ?, ?, ?) RETURNING buying_service.object_id";
    
    private static final String CREATE_BUYING_SERVICE_RECIPIENT = 
            "insert into buying_service_recipient(name_kor, name_eng, transit_nr, "
            + "phonenumber_first, phonenumber_second, zip_code,"
            + "address, userComment, fk_buying_service) values (?, ?, ?,   ?, ?, ?,   ?, ?, ?)";
    
    private static final String CREATE_BUYING_SERVICE_PAYMENT =  
            "insert into buying_service_payment(buying_service_payment_state, fk_buying_service) values(?, ?)";
    
    private static final String CREATE_BUYING_PRODUCT = 
            "insert into buying_service_product(pd_categorytitle, pd_itemtitle, pd_brandname, "
            + "pd_itemname, pd_amount, pd_price, "
            + "pd_totalprice, fk_buying_service) values(?, ?, ?,   ?, ?, ?,   ?, ?)";
    
    @Autowired
    private BuyingServiceData buyingServiceData;
    
    @Autowired
    private RecipientData recipientData;
    
    @Override
    public EstimationService fastEstimationBuyingService(HashMap<String, Object>[] data, String userid) {
        double productsValue = Double.parseDouble(data[0].get("productsValue").toString());
        double deliveryValue = Double.parseDouble(data[1].get("deliveryValue").toString());
        double currentEurToKRW = getCurrentEurToKrw();
        double totalPrice = productsValue + deliveryValue;
        EstimationService estimation = new EstimationService();
        estimation.setResultPrice(getEstimationBuyingService(currentEurToKRW, totalPrice));        
        return estimation;
    }
    
    @Override
    public EstimationService estimationBuyingService(HashMap<String, Object>[] data, String userid) {
        
        ObjectMapper mapper = new ObjectMapper();
        BuyingProduct[] buyingProducts = null;
        buyingServiceData.setShopDeliveryPrice(Double.parseDouble(data[0].get("shopDeliveryPrice").toString()));
        try {
            buyingProducts = mapper.readValue(data[1].get("productContentObjectList").toString(), BuyingProduct[].class);
        } catch (IOException e) {
            LOGGER.error("Mapping of estimationService is failed:"+ userid, e);
        }
        buyingServiceData.setBuyingProductsList(buyingProducts);
        double currentEurToKRW = getCurrentEurToKrw();
        
        double totalPrice = buyingServiceData.getBuyingProductsPriceSum() + buyingServiceData.getShopDeliveryPrice();
        
        EstimationService estimation = new EstimationService();
        estimation.setResultPrice(getEstimationBuyingService(currentEurToKRW, totalPrice));
        
        return estimation;
    }
    
    private double getCurrentEurToKrw() {
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
          = restTemplate.getForEntity(CURRENTCY_SERVICE_URL, String.class);
        
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject)parser.parse(response.getBody());
        System.out.println(response.getBody());
        JsonElement jsonElement = jsonObject.get("rates");
        
        JsonObject rates = jsonElement.getAsJsonObject();
        //System.out.println(rates.get("KRW").getAsDouble());
        return rates.get("KRW").getAsDouble();
    }
    
    public int getEstimationBuyingService(double currentEurToKRW, double totalPrice) {
        double feePercent = ConfigurationData.BUYING_SERVICE_FEE_PERCENT;
        double result = (currentEurToKRW*totalPrice)*(1 + feePercent);
//        if (mergeBox) {
//            double mergingBoxFee = ConfigurationData.MERGING_BOX_FEE;
//            result = result + mergingBoxFee;
//        }
        return mathCeilDigit(2, result);
    }
    
    private int mathCeilDigit(int digit, double price) {
        int power = (int) Math.pow(10, digit);
        int newPrice = (int) Math.ceil(price/power);
        return (newPrice*power);
    }
    
    public ResponseEntity<?> createBuyingService(@RequestBody HashMap<String, Object>[] data, String userid){
        String orderid = OrderID.generateOrderID();
        LOGGER.error("Creating BuyingService is started", orderid);
        LocalDate orderDate = TimeStamp.getRequestDate();
        ObjectMapper mapper = new ObjectMapper();
        BuyingProduct[] buyingProducts = null;
        buyingServiceData.setUserid(userid);
        buyingServiceData.setOrderid(orderid);
        buyingServiceData.setOrderDate(orderDate);
        buyingServiceData.setShopUrl(data[0].get("shopUrl").toString());
        buyingServiceData.setBuyingPrice(Double.parseDouble(data[1].get("buyingPrice").toString()));
        
        try {
            buyingProducts = mapper.readValue(data[2].get("productContentObjectList").toString(), BuyingProduct[].class);
        } catch (IOException e) {
            LOGGER.error("Mapping of buyingProductsList is failed:"+ userid + "/" + orderid, e);
        }
        buyingServiceData.setBuyingProductsList(buyingProducts);
        
        try {
            recipientData = mapper.readValue(data[3].get("recipientObjectData").toString(), RecipientData.class);
        } catch (IOException e) {
            LOGGER.error("Mapping of recipientData is failed:"+ userid + "/" + orderid, e);
        }
        buyingServiceData.setRecipientData(recipientData);
        
        buyingServiceData.setShippingPrice(INITIAL_SHIP_PRICE);
        buyingServiceData.setBuyingState(BuyingServiceState.PRODUCT_PAYMENT_READY);
        buyingServiceData.setBuyingServicePaymentState(BuyingServicePaymentState.PRODUCT_PAYMENT_READY);
        
        return createBuyingService(buyingServiceData);
    }
    
    
    // do configure
    private ResponseEntity<?> createBuyingService(BuyingServiceData buyingservicedata){
        ConnectionDB.connectSQL();
        ResultSet resultSet = null;
        int buyingServiceId = 0;
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(CREATE_BUYING_SERVICE);) {
            psmt.setString(1, buyingservicedata.getUserid());
            psmt.setString(2, buyingservicedata.getOrderid());
            psmt.setDouble(3, buyingservicedata.getBuyingPrice());
            psmt.setInt(4, buyingservicedata.getBuyingState().getCode());
            psmt.setString(5, buyingservicedata.getShopUrl());
            psmt.setDate(6, java.sql.Date.valueOf(TimeStamp.getRequestDate()));
            resultSet  = psmt.executeQuery();
            buyingServiceId = getBuyingServiceId(resultSet);
            buyingservicedata.setBuyingServiceid(buyingServiceId);
        } catch (SQLException e) {
              LOGGER.error("Creating BuyingService is failed", e);
        }
        
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(CREATE_BUYING_SERVICE_RECIPIENT);) {
            RecipientData recipientData = buyingservicedata.getRecipientData();
            psmt.setString(1, recipientData.getNameKor());
            psmt.setString(2, recipientData.getNameEng());
            psmt.setString(3, recipientData.getTransitNr());
            psmt.setString(4, recipientData.getPhonenumberFirst());
            psmt.setString(5, recipientData.getPhonenumberSecond());
            psmt.setString(6, recipientData.getZipCode());
            psmt.setString(7, recipientData.getAddress());
            psmt.setString(8, recipientData.getUsercomment());
            psmt.setInt(9, buyingservicedata.getBuyingServiceid());
            psmt.executeUpdate();
        } catch (SQLException e) {
              LOGGER.error("Creating BuyingService is failed", e);
        }
        
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(CREATE_BUYING_SERVICE_PAYMENT);) {
            psmt.setInt(1, buyingservicedata.getBuyingServicePaymentState().getCode());
            psmt.setInt(2, buyingservicedata.getBuyingServiceid());
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
                    psmt.setString(1, products.get(i).getCategoryTitle());
                    psmt.setString(2, products.get(i).getItemTitle());
                    psmt.setString(3, products.get(i).getBrandName());
                    psmt.setString(4, products.get(i).getItemName());
                    psmt.setInt(5, products.get(i).getProductAmount());
                    psmt.setDouble(6, products.get(i).getProductPrice());
                    psmt.setDouble(7, products.get(i).getProductTotalPrice());
                    psmt.setInt(8, buyingServiceData.getBuyingServiceid());
                    psmt.executeUpdate();
                }
            } catch (SQLException ex) {
                LOGGER.error("Creating BuyingServiceProducts is failed", ex);
            }
    }
    
    private int getBuyingServiceId(ResultSet rs) throws SQLException {
        int buyingServiceId = 0;
        while (rs.next()) {
            buyingServiceId = rs.getInt("object_id");
        }
        return buyingServiceId;
    }

    @Override
    public UserBaseInfo getUserBaseInfo(String userid) {
        return CustomerStatusDB.getUserBaseInfo(userid);
    }

    @Override
    public ResponseEntity<?> registerFavoriteAddress(HashMap<String, Object>[] data, String userid) {
        ObjectMapper mapper = new ObjectMapper();
        FavoriteAddress favoriteAddress = null;
        try {
            favoriteAddress = mapper.readValue(data[0].get("favoriteAddressData").toString(), FavoriteAddress.class);
            favoriteAddress.setUserid(userid);
        } catch (IOException ex) {
            String error = "Error mapping for registering favoriteAddress";
            LOGGER.error(error, ex);
        }
        return AddressManagerDB.createFavoriteAddress(favoriteAddress, userid);
    }
}
package com.gkoo.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkoo.data.ConfigurationData;
import com.gkoo.data.EstimationService;
import com.gkoo.data.RecipientData;
import com.gkoo.data.buyingservice.BuyingProduct;
import com.gkoo.data.buyingservice.BuyingServiceModel;
import com.gkoo.repository.BuyingServiceRepository;
import com.gkoo.service.BuyingService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import util.OrderID;
import util.TimeStamp;

@Service
public class BuyingServiceImpl implements BuyingService {
    private final String currencyServiceUrl = "https://api.exchangeratesapi.io/latest?base=EUR";
    private final BuyingServiceRepository buyingServiceRepository;
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private BuyingServiceModel buyingServiceModel;
    
    @Autowired
    private RecipientData recipientData;
    
    @Autowired
    public BuyingServiceImpl(BuyingServiceRepository buyingServiceRepository) {
        this.buyingServiceRepository = buyingServiceRepository;
    }
    
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
        ObjectMapper mapper = new ObjectMapper();
        BuyingProduct[] buyingProducts = null;
        buyingServiceModel.setOrderId(orderid);
        buyingServiceModel.setOrderDate(orderDate);
        buyingServiceModel.setShopUrl(data[0].get("shopUrl").toString());
        
        try {
            buyingProducts = mapper.readValue(data[1].get("productContentObjectList").toString(), BuyingProduct[].class);
        } catch (IOException e) {
            LOGGER.error("Mapping of buyingProductsList is failed:"+ userid + "/" + orderid, e);
        }
        buyingServiceModel.setBuyingProductsList(buyingProducts);
        
        try {
            recipientData = mapper.readValue(data[2].get("recipientObjectData").toString(), RecipientData.class);
        } catch (IOException e) {
            LOGGER.error("Mapping of recipientData is failed:"+ userid + "/" + orderid, e);
        }
        buyingServiceModel.setRecipientData(recipientData);
        return buyingServiceRepository.createBuyingService(buyingServiceModel);
    }
}
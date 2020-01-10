package com.gkoo.service.impl;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import com.gkoo.data.ConfigurationData;
import com.gkoo.data.EstimationService;
import com.gkoo.repository.BuyingServiceRepository;
import com.gkoo.repository.CustomerStatusRepository;
import com.gkoo.service.BuyingService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class BuyingServiceImpl implements BuyingService {
    private final String currencyServiceUrl = "https://api.exchangeratesapi.io/latest?base=EUR";
    private final BuyingServiceRepository buyingServiceRepository;
    
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
        return buyingServiceRepository.createBuyingService()data, userid;
    }
}
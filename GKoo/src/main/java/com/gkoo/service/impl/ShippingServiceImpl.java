


package com.gkoo.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.data.UserBaseInfo;
import com.gkoo.repository.ShippingServiceRepository;
import com.gkoo.service.ShippingService;
import payment.PaymentState;
import shippingService.DeliveryDataObject;
import shippingService.ShippingProduct;
import shippingService.ShippingServiceModel;
import shippingService.ShippingServiceState;
import util.OrderID;
import util.TimeStamp;

@Service
public class ShippingServiceImpl implements ShippingService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final double INITIAL_PRICE = 0;
    
    private ShippingServiceRepository shippingServiceRepository;
    
    @Autowired
    public ShippingServiceImpl(ShippingServiceRepository shippingServiceRepository) {
        this.shippingServiceRepository = shippingServiceRepository;
    }
    
    @Override
    public ResponseEntity<?> requestShippingservice(HashMap<String, Object>[] data, String userid) {
        String timeStamp = TimeStamp.getCurrentTimeStampKorea();
        String orderId = OrderID.generateOrderID();
        LocalDate orderDate = TimeStamp.getRequestDate();
        LOGGER.info("배송대행 서비스 신청: "+ userid + "/배송대행 서비스주문번호: " + orderId);
        
        ShippingServiceModel shippingModel = new ShippingServiceModel();
        shippingModel.setUserid(userid);
        shippingModel.setTimeStamp(timeStamp);
        shippingModel.setOrderId(orderId);
        shippingModel.setEasyship(data[0].get("easyship").toString());
        shippingModel.setOrderDate(orderDate);
        
        ObjectMapper mapper = new ObjectMapper();
        DeliveryDataObject deliveryDataObj = null;
        try {
            deliveryDataObj = mapper.readValue(data[1].get("deliveryDataObject").toString(), DeliveryDataObject.class);
        } catch (IOException e) {
            LOGGER.error("Mapping of deliveryDataObject is failed:" + userid + "/" + orderId, e);
        }
        
        ShippingProduct[] shippingProducts = null;
        try {
            shippingProducts = mapper.readValue(data[2].get("shippingProductList").toString(), ShippingProduct[].class);
        } catch (IOException e) {
            LOGGER.error("Mapping of shippingProductList is failed:"+ userid + "/" + orderId, e);
        }
        
        shippingModel.setDeliveryData(deliveryDataObj);
        shippingModel.setShippingProductsList(shippingProducts);

        /**
         *ToDO: move to shippingServiceModel
         *ToDO: Build pattern
         *ToDO: Jackson
         */
        shippingModel.setReceiverNameByKorea(data[3].get("receiverNameByKorea").toString());
        shippingModel.setOwnerContent(data[4].get("setOwnerContent").toString());
        shippingModel.setReceiverNameByEnglish(data[5].get("receiverNameByEnglish").toString());
        
        shippingModel.setPrivateTransit(data[6].get("privateTransit").toString());
        shippingModel.setTransitNumber(data[7].get("transitNumber").toString());
        shippingModel.setAgreeWithCollection(data[8].get("agreeWithCollection").toString());
        
        shippingModel.setPhonenumberFirst(data[9].get("phonenumberFirst").toString());
        if(data[10].get("phonenumberSecond") == null) {
            shippingModel.setPhonenumberSecond("");
        } else {
            shippingModel.setPhonenumberSecond(data[10].get("phonenumberSecond").toString());
        }
        
        shippingModel.setPostCode(data[11].get("postCode").toString());
        shippingModel.setDeliveryAddress(data[12].get("deliveryAddress").toString());
        if(data[13].get("deliveryMessage") == null) {
            shippingModel.setDeliveryMessage("");
        } else {
            shippingModel.setDeliveryMessage(data[13].get("deliveryMessage").toString());
        }
        
        /** 국제배송비 */
        shippingModel.setShippingPrice(INITIAL_PRICE);
        /** 국제배송 상태 */
        shippingModel.setShipState(ShippingServiceState.RECEIVE_BOX_READY);
        shippingModel.setPaymentState(PaymentState.NOT_DEFINED);
        
        return shippingServiceRepository.createShippingService(shippingModel);
    }
    
    @Override
    public UserBaseInfo getUserBaseInfo(String userid) {
        return shippingServiceRepository.getUserBaseInfo(userid);
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
        return shippingServiceRepository.registerFavoriteAddress(favoriteAddress, userid);
    }

}

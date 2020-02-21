package com.gkoo.service.impl;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.gkoo.data.BuyingOrderData;
import com.gkoo.data.DeliveryKoreaData;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import com.gkoo.db.MypageDB;
import com.gkoo.repository.MypageRepository;
import com.gkoo.service.MypageService;
import payment.PaymentData;

@Service
public class MypageServiceImpl implements MypageService {
    private final MypageRepository mypageRepository;
    
    @Autowired
    public MypageServiceImpl(MypageRepository mypageRepository) {
        this.mypageRepository = mypageRepository;
    }
    
    public List<OrderInformation> getOrderData(String userid){
        return mypageRepository.getOrderData(userid);
    }

    @Override
    public List<WarehouseInformation> getWarehouseData(String userid) {
        return mypageRepository.getWarehouseData(userid);
    }

    @Override
    public ResponseEntity<?> updateTrackingNumber(String userid,String orderid, String trackingCompany,String trackingNumber) {
        return mypageRepository.updateTrackingNumber(userid, orderid, trackingCompany, trackingNumber);
    }

    @Override
    public List<PaymentData> getPaymentData(String userid) {
        return mypageRepository.getPaymentData(userid);
    }

    @Override
    public List<DeliveryKoreaData> getDeliveryKoreaData(String userid) {
        return mypageRepository.getDeliveryKoreaData(userid);
    }

    @Override
    public List<BuyingOrderData> getOrderDataBuyingService(String userid) {
        return MypageDB.getOrderDataBuyingService(userid);
    }

    @Override
    public List<PaymentData> getPaymentDataBuyingService(String userid) {
        return MypageDB.getPaymentDataBuyingService(userid);
    }

    @Override
    public List<DeliveryKoreaData> getDeliveryKoreaDataBuyingService(String userid) {
        return MypageDB.getDeliveryKoreaDataBuyingService(userid);
    }

    @Override
    public List<PaymentData> getPaymentDeliveryBuyingService(String userid) {
        return MypageDB.getPaymentDeliveryBuyingService(userid);
    }

    @Override
    public ResponseEntity<?> updatePaymentProductBuyingService(HashMap<String, Object>[] data, String userid) {
        int objectid = Integer.parseInt(data[0].get("paymentid").toString());
        String paymentOwnername = data[1].get("paymentOwnername").toString();
        int paymentArt = Integer.parseInt(data[2].get("paymentArt").toString());
        return MypageDB.updatePaymentProductBuyingService(objectid, paymentOwnername, paymentArt);
    }
    
    @Override
    public ResponseEntity<?> updatePaymentDeliveryBuyingService(HashMap<String, Object>[] data, String userid) {
        int objectid = Integer.parseInt(data[0].get("paymentid").toString());
        String paymentOwnername = data[1].get("paymentOwnername").toString();
        int paymentArt = Integer.parseInt(data[2].get("paymentArt").toString());
        return MypageDB.updatePaymentDeliveryBuyingService(objectid, paymentOwnername, paymentArt);
    }
}
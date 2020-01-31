package com.gkoo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.gkoo.data.DeliveryKoreaData;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import com.gkoo.data.buyingservice.BuyingServiceData;
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
    public BuyingServiceData getMypageBuyingServiceData(String userid) {
        return null;
    }
}
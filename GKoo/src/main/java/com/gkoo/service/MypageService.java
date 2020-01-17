package com.gkoo.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.DeliveryKoreaData;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import com.gkoo.data.buyingservice.BuyingServiceData;
import payment.PaymentData;

public interface MypageService {
    public List<OrderInformation> getOrderData(String userid);
    public List<WarehouseInformation> getWarehouseData(String userid);
    public ResponseEntity<?> updateTrackingNumber(String userid, String orderNumber, String trackingCompany, String trackingNumber);
    public List<PaymentData> getPaymentData(String userid);
    public List<DeliveryKoreaData> getDeliveryKoreaData(String userid);
    public BuyingServiceData getMypageBuyingServiceData(String userid);
    
}

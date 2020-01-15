package com.gkoo.repository;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.DeliveryKoreaData;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import payment.PaymentData;

public interface MypageRepository {
    public List<OrderInformation> getOrderData(String userid);
    public List<WarehouseInformation> getWarehouseData(String userid);
    public ResponseEntity<?> updateTrackingNumber(String userid,String orderid, String trackingCompany,String trackingNumber);
    public List<PaymentData> getPaymentData(String userid);
    public List<DeliveryKoreaData> getDeliveryKoreaData(String userid);
}

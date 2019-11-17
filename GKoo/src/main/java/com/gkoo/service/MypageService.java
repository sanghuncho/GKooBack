package com.gkoo.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;

/**
 *
 * @author sanghuncho
 *
 * @since  18.11.2019
 *
 */
public interface MypageService {
    public List<OrderInformation> getOrderData(String userid);
    
    public List<WarehouseInformation> getWarehouseData(String userid);
    
    public ResponseEntity<?> updateTrackingNumber(String memberId,String orderNumber,String trackingCompany,String trackingNumber);
}

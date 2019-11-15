package com.gkoo.repository;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;

public interface MypageRepository {
    public List<OrderInformation> getOrderData(String userid);
    
    public List<WarehouseInformation> getWarehouseData(String userid);
    
    public ResponseEntity<?> updateTrackingNumber(String memberId,String orderNumber,String trackingCompany,String trackingNumber);
}

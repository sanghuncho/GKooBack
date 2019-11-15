package com.gkoo.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;

public interface MypageService {
    public List<OrderInformation> getOrderData(String userid);
    
    public List<WarehouseInformation> getWarehouseData(String userid);
    
    public ResponseEntity<?> updateTrackingNumber(String memberId,String orderNumber,String trackingCompany,String trackingNumber);
}

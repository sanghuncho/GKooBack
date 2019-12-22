package com.gkoo.repository.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import com.gkoo.db.MypageDB;
import com.gkoo.repository.MypageRepository;    

public class MypageRepositoryImpl implements MypageRepository {
    private static final Logger LOGGER = LogManager.getLogger();
    
    @Override
    public List<OrderInformation> getOrderData(String userid) {
        return MypageDB.getOrderData(userid);
    }
    
    public List<WarehouseInformation> getWarehouseData(String userid){
        return MypageDB.getWarehouseData(userid);
    }
    
    public ResponseEntity<?> updateTrackingNumber(String memberId,String orderNumber,String trackingCompany,String trackingNumber){
        return MypageDB.updateTrackingNumber(memberId, orderNumber, trackingCompany, trackingNumber);
    }
}

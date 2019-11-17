package com.gkoo.repository.impl;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import com.gkoo.db.MypageDB;
import com.gkoo.repository.MypageRepository;

/**
 *
 * @author sanghuncho
 *
 * @since  18.11.2019
 *
 */
public class MypageRepoImpl implements MypageRepository {

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

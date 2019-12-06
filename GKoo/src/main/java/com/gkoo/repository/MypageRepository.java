package com.gkoo.repository;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;

public interface MypageRepository {
    public List<OrderInformation> getOrderData(String userid);
    
    public List<WarehouseInformation> getWarehouseData(String userid);
    
    public ResponseEntity<?> updateTrackingNumber(String memberId,String orderNumber,String trackingCompany,String trackingNumber);
    
    public List<FavoriteAddress> getFavoriteAddressList(String userid);
    
    public ResponseEntity<?> updateFavoriteAddress(String userid, HashMap<String, Object>[] data);
    
    public ResponseEntity<?> createFavoriteAddress(String userid, HashMap<String, Object>[] data);
}

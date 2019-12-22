package com.gkoo.service.impl;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import com.gkoo.repository.MypageRepository;
import com.gkoo.service.MypageService;

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
    public ResponseEntity<?> updateTrackingNumber(String memberId,String orderNumber,String trackingCompany,String trackingNumber) {
        return mypageRepository.updateTrackingNumber(memberId, orderNumber, trackingCompany, trackingNumber);
    }
}
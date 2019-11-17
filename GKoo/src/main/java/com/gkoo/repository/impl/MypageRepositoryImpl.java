package com.gkoo.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import com.gkoo.db.MypageDB;
import com.gkoo.repository.MypageRepository;
import databaseUtil.ConnectionDB;

public class MypageRepositoryImpl implements MypageRepository {

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

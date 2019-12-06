package com.gkoo.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.UserBaseInfo;
import com.gkoo.data.WarehouseInformation;
import com.gkoo.db.CustomerStatusDB;
import com.gkoo.db.MypageDB;
import com.gkoo.exception.CustomerStatusException;
import com.gkoo.repository.MypageRepository;
import databaseUtil.ConnectionDB;

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

    @Override
    public List<FavoriteAddress> getFavoriteAddressList(String userid) {
        return MypageDB.getFavoriteAddressList(userid);
    }

    @Override
    public ResponseEntity<?> updateFavoriteAddress(String userid, HashMap<String, Object>[] data) {
        ObjectMapper mapper = new ObjectMapper();
        FavoriteAddress favoriteAddress = null;
        try {
            favoriteAddress = mapper.readValue(data[0].get("favoriteAddress").toString(), FavoriteAddress.class);
        } catch (IOException ex) {
            String error = "Error mapping for updating favoriteAddress";
            LOGGER.error(error, ex);
            throw new CustomerStatusException(error, ex);
        }
        return MypageDB.updateFavoriteAddress(userid, favoriteAddress);
    }

    @Override
    public ResponseEntity<?> createFavoriteAddress(String userid, HashMap<String, Object>[] data) {
        ObjectMapper mapper = new ObjectMapper();
        FavoriteAddress favoriteAddress = null;
        try {
            favoriteAddress = mapper.readValue(data[0].get("favoriteAddress").toString(), FavoriteAddress.class);
        } catch (IOException ex) {
            String error = "Error mapping creating favoriteAddress";
            LOGGER.error(error, ex);
            throw new CustomerStatusException(error, ex);
        }
        return MypageDB.createFavoriteAddress(userid, favoriteAddress);
    }
}

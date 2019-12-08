package com.gkoo.repository.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.keycloak.representations.AccessToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkoo.data.CustomerStatus;
import com.gkoo.data.UserBaseInfo;
import com.gkoo.db.CustomerStatusDB;
import com.gkoo.exception.CustomerStatusException;
import com.gkoo.repository.CustomerStatusRepository;

@Repository
public class CustomerStatusRepoImpl implements CustomerStatusRepository {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void checkUserid(AccessToken accessToken) {
        String userid = accessToken.getPreferredUsername();
        String lastname = accessToken.getFamilyName();
        String firstname = accessToken.getGivenName();
        Boolean existUserid = null;
        try {
            existUserid = CustomerStatusDB.existUserid(userid);
        } catch (SQLException e) {
            LOGGER.error("CustomerStatusRepoImpl-checkUserid:" + userid, e);
        }
        if (!existUserid) {
            CustomerStatusDB.registerInitialCustomer(userid, lastname, firstname);
        }
    }
    
    @Override
    public CustomerStatus getCustomerStatus(String userid) {
        return CustomerStatusDB.getCustomerStatus(userid);
    }

    @Override
    public UserBaseInfo getUserBaseInfo(String userid) {
        return CustomerStatusDB.getUserBaseInfo(userid);
    }

    @Override
    public ResponseEntity<?> updateBaseInfo(HashMap<String, Object>[] data, String userid) {
        ObjectMapper mapper = new ObjectMapper();
        UserBaseInfo userBaseInfoData=null;
        //Objects.requireNonNull(data[0].get("userBaseInfo"));
        try {
            userBaseInfoData = mapper.readValue(data[0].get("userBaseInfoData").toString(), UserBaseInfo.class);
        } catch (IOException ex) {
            String error = "Error mapping userBaseInfo";
            LOGGER.error(error, ex);
            throw new CustomerStatusException(error, ex);
        }
        return CustomerStatusDB.updateBaseInfo(userBaseInfoData, userid);
    }
}
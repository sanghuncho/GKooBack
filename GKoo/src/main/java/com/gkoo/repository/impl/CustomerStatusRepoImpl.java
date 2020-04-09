package com.gkoo.repository.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> checkUserid(String userid, String lastname, String firstname) {
        String fullnameKor = lastname.concat(firstname);
        Boolean existUserid = null;
        try {
            existUserid = CustomerStatusDB.existUserid(userid);
        } catch (SQLException e) {
            LOGGER.error("CustomerStatusRepoImpl-checkUserid:" + userid, e);
        }
        if (!existUserid) {
            int lastPersonalBoxAddress = CustomerStatusDB.getPersonaBoxAddress();
            int personalBoxAddress = lastPersonalBoxAddress + 1;
            String personalBoxAddressStr = "GK" + personalBoxAddress;
            CustomerStatusDB.registerInitialCustomer(userid, fullnameKor, personalBoxAddressStr);
            CustomerStatusDB.updatePersonaBoxAddress(personalBoxAddress);
        }
        
        String responseMessage = "userid is checked:" + userid;
        return new ResponseEntity<String>(responseMessage, HttpStatus.ACCEPTED);
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
        try {
            userBaseInfoData = mapper.readValue(data[0].get("userBaseInfoData").toString(), UserBaseInfo.class);
        } catch (IOException ex) {
            String error = "Error mapping userBaseInfo:" + userid;
            LOGGER.error(error, ex);
            throw new CustomerStatusException(error, ex);
        }
        return CustomerStatusDB.updateBaseInfo(userBaseInfoData, userid);
    }
}
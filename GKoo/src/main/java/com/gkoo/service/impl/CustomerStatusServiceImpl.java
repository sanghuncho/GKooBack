package com.gkoo.service.impl;

import java.util.HashMap;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.gkoo.data.CustomerStatus;
import com.gkoo.data.UserBaseInfo;
import com.gkoo.repository.CustomerStatusRepository;
import com.gkoo.service.CustomerStatusService;

@Service
public class CustomerStatusServiceImpl implements CustomerStatusService {
    private final CustomerStatusRepository customerStatusRepository;
    
    @Autowired
    public CustomerStatusServiceImpl(CustomerStatusRepository customerStatusRepository) {
        this.customerStatusRepository = customerStatusRepository;
    }
    
    public void checkUserid(AccessToken accessToken) {
        customerStatusRepository.checkUserid(accessToken);;
    }

    @Override
    public CustomerStatus getCustomerStatus(String userid) {
        return customerStatusRepository.getCustomerStatus(userid);
    }

    @Override
    public UserBaseInfo getUserBaseInfo(String userid) {
        return customerStatusRepository.getUserBaseInfo(userid);
    }

    @Override
    public ResponseEntity<?> updateBaseInfo(HashMap<String, Object>[] data, String userid) {
        return customerStatusRepository.updateBaseInfo(data, userid);
    }
}

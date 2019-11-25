package com.gkoo.service.impl;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CustomerStatus getCustomerStatus(AccessToken accessToken) {
        return customerStatusRepository.getCustomerStatus(accessToken);
    }

    @Override
    public UserBaseInfo getUserBaseInfo(String userid) {
        return customerStatusRepository.getUserBaseInfo(userid);
    }
}

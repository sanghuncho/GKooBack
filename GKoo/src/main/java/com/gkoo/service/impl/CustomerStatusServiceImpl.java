package com.gkoo.service.impl;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import com.gkoo.data.CustomerStatus;
import com.gkoo.repository.CustomerStatusRepository;
import com.gkoo.service.CustomerStatusService;

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
}

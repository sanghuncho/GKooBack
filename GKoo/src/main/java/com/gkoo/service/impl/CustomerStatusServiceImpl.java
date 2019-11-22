package com.gkoo.service.impl;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gkoo.data.CustomerStatus;
import com.gkoo.repository.CustomerStatusRepository;
import com.gkoo.service.CustomerStatusService;

/**
 *
 * @author sanghuncho
 *
 * @since  18.11.2019
 *
 */
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
        //return new CustomerStatus("m", 0, 0, 1000);
    }
    
    public String getTestCustomer() {
        return "test";
    }
}

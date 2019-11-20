package com.gkoo.service.impl;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
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
}

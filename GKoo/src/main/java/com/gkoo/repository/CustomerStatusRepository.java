package com.gkoo.repository;

import org.keycloak.representations.AccessToken;
import com.gkoo.data.CustomerStatus;


/**
 *
 * @author sanghuncho
 *
 * @since  08.11.2019
 *
 */
public interface CustomerStatusRepository {
    
    public void checkUserid(AccessToken accessToken);
        
    public CustomerStatus getCustomerStatus(AccessToken accessToken);
}

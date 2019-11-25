package com.gkoo.repository;

import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Repository;
import com.gkoo.data.CustomerStatus;
import com.gkoo.data.UserBaseInfo;


/**
 *
 * @author sanghuncho
 *
 * @since  08.11.2019
 *
 */
@Repository
public interface CustomerStatusRepository {
    
    public void checkUserid(AccessToken accessToken);
    public CustomerStatus getCustomerStatus(AccessToken accessToken);
    public UserBaseInfo getUserBaseInfo(String userid);
}

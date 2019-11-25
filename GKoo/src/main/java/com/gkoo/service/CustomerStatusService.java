package com.gkoo.service;

import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Service;
import com.gkoo.data.CustomerStatus;
import com.gkoo.data.UserBaseInfo;


/**
 *
 * @author sanghuncho
 *
 * @since  08.11.2019
 *
 */
@Service
public interface CustomerStatusService {
    public CustomerStatus getCustomerStatus(AccessToken accessToken);
    public void checkUserid(AccessToken accessToken);
    public UserBaseInfo getUserBaseInfo(String userid);
}

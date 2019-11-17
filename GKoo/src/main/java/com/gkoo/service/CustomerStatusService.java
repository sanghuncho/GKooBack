package com.gkoo.service;

import org.keycloak.representations.AccessToken;
import com.gkoo.data.CustomerStatus;


/**
 *
 * @author sanghuncho
 *
 * @since  08.11.2019
 *
 */
public interface CustomerStatusService {
    public CustomerStatus getCustomerStatus(AccessToken accessToken);
    public void checkUserid(AccessToken accessToken);
}

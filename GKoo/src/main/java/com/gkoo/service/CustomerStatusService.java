package com.gkoo.service;

import java.util.HashMap;
import org.keycloak.representations.AccessToken;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.CustomerStatus;
import com.gkoo.data.UserBaseInfo;


/**
 *
 * @author sanghuncho
 *
 * @since  08.11.2019
 *
 */
public interface CustomerStatusService {
    public CustomerStatus getCustomerStatus(String userid);
    public void checkUserid(String userid, String lastname, String firstname);
    public UserBaseInfo getUserBaseInfo(String userid);
    public ResponseEntity<?> updateBaseInfo(HashMap<String, Object>[] data, String userid);
}
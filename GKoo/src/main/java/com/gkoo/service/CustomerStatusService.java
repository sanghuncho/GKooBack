package com.gkoo.service;

import java.util.HashMap;
import org.keycloak.representations.AccessToken;
import org.springframework.http.ResponseEntity;
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
    public CustomerStatus getCustomerStatus(String userid);
    public void checkUserid(AccessToken accessToken);
    public UserBaseInfo getUserBaseInfo(String userid);
    public ResponseEntity<?> updateBaseInfo(HashMap<String, Object>[] data, String userid);
<<<<<<< HEAD
}
=======
}
>>>>>>> 354bd9247ffe59b0bc99878a3f2dad7401adfeab

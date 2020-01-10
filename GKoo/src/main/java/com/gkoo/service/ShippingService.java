package com.gkoo.service;

import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.gkoo.data.UserBaseInfo;

public interface ShippingService {
    public ResponseEntity<?> requestShippingservice(@RequestBody HashMap<String, Object>[] data, String userid);
    public UserBaseInfo getUserBaseInfo(String userid);
    public ResponseEntity<?> registerFavoriteAddress(@RequestBody HashMap<String, Object>[] data, String userid);
}

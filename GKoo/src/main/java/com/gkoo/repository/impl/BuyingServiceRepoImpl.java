package com.gkoo.repository.impl;

import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.gkoo.repository.BuyingServiceRepository;

public class BuyingServiceRepoImpl implements BuyingServiceRepository {
    public ResponseEntity<?> createBuyingService(@RequestBody HashMap<String, Object>[] data, String userid){
        
        return null;
    }
}

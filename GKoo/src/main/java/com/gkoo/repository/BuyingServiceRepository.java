package com.gkoo.repository;

import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface BuyingServiceRepository {
    public ResponseEntity<?> createBuyingService(@RequestBody HashMap<String, Object>[] data, String userid);
}

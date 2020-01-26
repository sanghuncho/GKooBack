package com.gkoo.repository.impl;

import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import com.gkoo.data.buyingservice.BuyingServiceModel;
import com.gkoo.repository.BuyingServiceRepository;

@Repository
public class BuyingServiceRepoImpl implements BuyingServiceRepository {
    public ResponseEntity<?> createBuyingService(BuyingServiceModel buyingServiceModel){
        
        return null;
    }
}

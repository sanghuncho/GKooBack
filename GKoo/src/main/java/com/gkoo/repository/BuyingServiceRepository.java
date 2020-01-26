package com.gkoo.repository;

import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.gkoo.data.buyingservice.BuyingServiceModel;

public interface BuyingServiceRepository {
    public ResponseEntity<?> createBuyingService(BuyingServiceModel buyingServiceModel);
}

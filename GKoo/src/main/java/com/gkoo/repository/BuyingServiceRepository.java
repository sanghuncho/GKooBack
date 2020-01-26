package com.gkoo.repository;

import org.springframework.http.ResponseEntity;
import com.gkoo.data.buyingservice.BuyingServiceData;

public interface BuyingServiceRepository {
    public ResponseEntity<?> createBuyingService(BuyingServiceData buyingServiceData);
}

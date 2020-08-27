package com.gkoo.service;

import java.util.HashMap;
import org.springframework.http.ResponseEntity;

public interface AuctionService {
    public ResponseEntity<?> requestAuctionBidService(HashMap<String, Object>[] data, String userid);
}

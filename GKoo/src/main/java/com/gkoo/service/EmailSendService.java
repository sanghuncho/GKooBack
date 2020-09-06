package com.gkoo.service;

import java.util.HashMap;
import org.springframework.http.ResponseEntity;

public interface EmailSendService {
    public ResponseEntity<?> requestAuctionDeposit(HashMap<String, Object>[] data);
    public ResponseEntity<?> requestAuctionBid(HashMap<String, Object>[] data);
}

package com.gkoo.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.AuctionBidData;

public interface AuctionService {
    public ResponseEntity<?> requestAuctionBidService(HashMap<String, Object>[] data);
    public List<AuctionBidData> requestAuctionBidDataList(String userid);
    public ResponseEntity<?> deleteAuctionBidService(HashMap<String, Object>[] data);
}

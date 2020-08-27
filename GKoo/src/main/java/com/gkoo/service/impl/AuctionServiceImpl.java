package com.gkoo.service.impl;

import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.AuctionBidData;
import com.gkoo.db.AuctionServiceDB;
import com.gkoo.service.AuctionService;
import util.TimeStamp;

public class AuctionServiceImpl implements AuctionService {

    @Override
    public ResponseEntity<?> requestAuctionBidService(HashMap<String, Object>[] data,
            String userid) {
        
        //ObjectMapper mapper = new ObjectMapper();
        
        AuctionBidData bidData = new AuctionBidData();
        bidData.setProductUrl(data[0].get("productUrl").toString());
        bidData.setBidValue(Double.parseDouble(data[1].get("bidValue").toString()));
        bidData.setAuctionMessage(data[2].get("auctionMessage").toString());
        bidData.setAuctionBidDate(TimeStamp.getRequestDate());
        
        return AuctionServiceDB.requestAuctionBidService(bidData, userid);
    }

}

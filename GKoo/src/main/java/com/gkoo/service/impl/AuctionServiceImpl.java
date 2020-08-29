package com.gkoo.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.gkoo.data.AuctionBidData;
import com.gkoo.db.AuctionServiceDB;
import com.gkoo.service.AuctionService;
import util.TimeStamp;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Override
    public ResponseEntity<?> requestAuctionBidService(HashMap<String, Object>[] data) {
        AuctionBidData bidData = new AuctionBidData();
        bidData.setUserid(data[0].get("userid").toString());
        bidData.setProductUrl(data[1].get("productUrl").toString());
        bidData.setBidValue(Double.parseDouble(data[2].get("bidValue").toString()));
        bidData.setAuctionMessage(data[3].get("auctionMessage").toString());
        bidData.setAuctionBidDate(TimeStamp.getRequestDate());
        
        return AuctionServiceDB.requestAuctionBidService(bidData);
    }

    @Override
    public List<AuctionBidData> requestAuctionBidDataList(String userid) {
        return AuctionServiceDB.requestAuctionBidDataList(userid);
    }

    @Override
    public ResponseEntity<?> deleteAuctionBidService(HashMap<String, Object>[] data) {
        AuctionBidData bidData = new AuctionBidData();
        bidData.setObjectid(Integer.parseInt(data[0].get("objectid").toString()));
        bidData.setUserid(data[1].get("userid").toString());
        return null;
    }
}
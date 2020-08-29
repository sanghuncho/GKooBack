package com.gkoo.data;

import java.time.LocalDate;
import com.gkoo.enums.AuctionResult;

public class AuctionBidData {
    private int objectid;
    private String userid;
    private String productUrl;
    private double bidValue;
    private String auctionMessage;
    private LocalDate auctionBidDate;
    private AuctionResult auctionResult ;
    
    public AuctionBidData() {
    }
    
    public int getObjectid() {
        return objectid;
    }
    public void setObjectid(int objectid) {
        this.objectid = objectid;
    }
    
    public java.sql.Date getAuctionBidDate() {
        return java.sql.Date.valueOf(auctionBidDate);
    }
    
    public void setAuctionBidDate(LocalDate auctionBidDate) {
        this.auctionBidDate = auctionBidDate;
    }
    
    public String getProductUrl() {
        return productUrl;
    }
    
    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
    
    public double getBidValue() {
        return bidValue;
    }
    
    public void setBidValue(double bidValue) {
        this.bidValue = bidValue;
    }
    
    public String getAuctionMessage() {
        return auctionMessage;
    }
    
    public void setAuctionMessage(String auctionMessage) {
        this.auctionMessage = auctionMessage;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public AuctionResult getAuctionResult() {
        return auctionResult;
    }

    public void setAuctionResult(AuctionResult auctionResult) {
        this.auctionResult = auctionResult;
    }
}
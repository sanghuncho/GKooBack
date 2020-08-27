package com.gkoo.data;

import java.time.LocalDate;

public class AuctionBidData {
    private int objectid;
    private String productUrl;
    private double bidValue;
    private String auctionMessage;
    private LocalDate auctionBidDate;
    
    public AuctionBidData() {
    }
    
    public int getObjectid() {
        return objectid;
    }
    public void setObjectid(int objectid) {
        this.objectid = objectid;
    }
    
    public LocalDate getAuctionBidDate() {
        return auctionBidDate;
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
}

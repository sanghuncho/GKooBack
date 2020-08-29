package com.gkoo.enums;

public enum AuctionResult {
    
    //입찰접수
    RESULT_INITIAl(0),
    
    //입찰접수
    BID_READY(1),
    
    //입찰완료
    BID_COMPLETED(2),
    
    //낙찰
    AUCTION_FAILED(3),
    
    //유찰
    AUCTION_SUCCESS(4),
    
    //URL 에러
    URL_UNKNOWN(5);

    private final int stateCode;
    
    AuctionResult(int stateCode){
        this.stateCode = stateCode;
    }
    
    public int getCode() {
        return this.stateCode;
    }
    
    public static AuctionResult getAuctionResult(int stateCode) {
        AuctionResult result = AuctionResult.RESULT_INITIAl;
        switch(stateCode) {
            case 1 : 
                result = AuctionResult.BID_READY;
                break;
            case 2 : 
                result = AuctionResult.BID_COMPLETED;
                break;
            case 3 : 
                result = AuctionResult.AUCTION_FAILED;
                break;
            case 4 : 
                result = AuctionResult.AUCTION_SUCCESS;
                break;
            case 5 : 
                result = AuctionResult.URL_UNKNOWN;
                break;
        }
        return result;
    }
}

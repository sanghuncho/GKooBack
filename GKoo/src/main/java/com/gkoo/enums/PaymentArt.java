package com.gkoo.enums;

public enum PaymentArt {
    
    //무통장 입금
    TRANSFER_BANK(1);

    private final int stateCode;
    
    PaymentArt(int stateCode){
        this.stateCode = stateCode;
    }
    
    public int getCode() {
        return this.stateCode;
    }
}

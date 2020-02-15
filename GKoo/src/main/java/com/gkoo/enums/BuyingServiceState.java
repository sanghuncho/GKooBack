package com.gkoo.enums;

/**
 *
 * @author sanghuncho
 *
 * @since  10.01.2020
 *
 */
public enum BuyingServiceState {
    
    //구매대행  신청함
    
    //구매대행 결제대기    
    PRODUCT_PAYMENT_READY(1),
    
    //구매대행 결제완료
    PRODUCT_PAYMENT_COMPLETION(2),
    
    //입고대기
    //RECEIVE_BOX_READY(3),
    
    //배송비 및 추가서비스 결제요청
    DELIVERY_PAYMENT_READY(3),
    
    //배송비 및 추가서비스 결제완료
    DELIVERY_PAYMENT_COMPLETION(4),
    
    //해외배송중
    DELIVERY_WORLD(5),
    
    //통관진행
    IMPORT_CENTER(6),
    
    //국내배송
    DELIVERY_KOREA(7),
    
    //배송완료
    DELIVERY_FINISHED(8);
  
    private final int stateCode;

    BuyingServiceState(int stateCode) {
        this.stateCode = stateCode;
    }
    
    public int getCode() {
        return this.stateCode;
    }
    
    public String getBuyingStateName(int stateCode) {
        String stateName="";
        switch(stateCode) {
            case 1 : 
                stateName = "구매대행 결제대기";
                break;
            case 2 : 
                stateName = "구매대행 결제완료";
                break;
            case 3 : 
                stateName = "배송비 결제대기";
                break;
            case 4 : 
                stateName = "배송비 결제완료";
                break;
            case 5 : 
                stateName = "해외배송중";
                break;
            case 6 : 
                stateName = "통관진행중";
                break;
            case 7 : 
                stateName = "국내배송중";
                break;
            case 8 : 
                stateName = "배소완료";
                break;
        }
        return stateName;
    }
}
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
    RECEIVE_BOX_READY(3),
    
    //배송비 및 추가서비스 결제요청
    DELIVERY_PAYMENT_READY(4),
    
    //배송비 및 추가서비스 결제완료
    DELIVERY_PAYMENT_COMPLETION(5),
    
    //해외배송중
    DELIVERY_WORLD(6),
    
    //통관진행
    IMPORT_CENTER(7),
    
    //국내배송
    DELIVERY_KOREA(8),
    
    //배송완료
    DELIVERYFINISHED(9);
  
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
                stateName = "입고대기";
                break;
            case 4 : 
                stateName = "결제완료";
                break;
            case 5 : 
                stateName = "입고대기";
                break;
            case 6 : 
                stateName = "입고완료";
                break;
            case 7 : 
                stateName = "추가 결제요청";
                break;
            case 8 : 
                stateName = "추가 결제완료";
                break;
            case 9 : 
                stateName = "해외배송중";
                break;
            case 10 : 
                stateName = "통관진행";
                break;
            case 11 : 
                stateName = "국내배송";
                break;
            case 12 : 
                stateName = "배송완료";
                break;
        }
        return stateName;
    }
}
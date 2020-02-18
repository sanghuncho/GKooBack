package com.gkoo.enums;

/**
 *
 * @author sanghuncho
 *
 * @since  13.01.2020
 *
 */
public enum BuyingServicePaymentState {
    
    //구매대행 결제대기    
    PRODUCT_PAYMENT_READY(1),
    
    //구매대행 결제완료(관리자결제확인함)
    PRODUCT_PAYMENT_COMPLETION(2),
    
    //배송비 및 추가 결제요청
    DELIVERY_PAYMENT_READY(3),
    
    //배송비 및 추가  결제완료
    DELIVERY_PAYMENT_COMPLETION(4),
    
    //전체 결제완료
    BUYING_SERVICE_PAYMENT_COMPLETION(5);
    
    private final int stateCode;
    
    BuyingServicePaymentState(int stateCode){
        this.stateCode = stateCode;
    }
    
    public int getCode() {
        return this.stateCode;
    }
    
    public String getBuyingServicePaymentStateName(int stateCode) {
        String stateName="";
        switch(stateCode) {
            case 1 : 
                stateName = "구매대행 결제대기";
                break;
            case 2 : 
                stateName = "구매대행 결제완료";
                break;
            case 3 : 
                stateName = "배송비 및 추가비용 결제요청";
                break;
            case 4 : 
                stateName = "배송비 및 추가비용 결제완료";
                break;
        }
        return stateName;
    }
}

package payment;

/**
 *	payment
 *  0 상품도착전 - 결제창 숨김
 *	1 결제대기 - 상품도착, 배송비 입력, 결제전
 *	2 결제요청 - 무통장입금신청
 *	3 결제완료 - 결제확인됨
 *
 * @author Sanghun Cho
 */

public enum PaymentState {

    NOT_DEFINED(0),
    
	ARRIVED_READY(1),
	
	REQUEST(2),
	
	COMPLETION(3);
	
	private final int stateCode;

	PaymentState(int stateCode) {
        this.stateCode = stateCode;
    }
	
	public int getCode() {
        return this.stateCode;
    }
}
package payment;

/**
 *	payment
 *	1 결제대기 - 결제전
 *	2 결제요청 - 무통장입금전
 *	3 결제완료
 *
 * @author Sanghun Cho
 */

public enum PaymentState {

	BEFORE(1),
	
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

package shippingService;

public enum ShippingServiceState {

		//입고대기
		RECEIVE_BOX_READY(1),
		
		//입고완료
		RECEIVE_BOX_COMPLETION(2),
		
		//결제요청
		PAYMENT_NOT_READY(3),
		
		//결제요청
		PAYMENT_READY(4),
		
		//결제완료
		PAYMENT_COMPLETION(5),
		
		//해외배송중
		DELIVERY_WORLD(6),
		//통관진행
		IMPORT_CENTER(7),
		
		//국내배송
		DELIVERY_KOREA(8),
		//배송완료
		DELIVERYFINISHED(9);
	  
		private final int stateCode;

		ShippingServiceState(int stateCode) {
	        this.stateCode = stateCode;
	    }
	    
	    public int getStateCode() {
	        return this.stateCode;
	    }
}

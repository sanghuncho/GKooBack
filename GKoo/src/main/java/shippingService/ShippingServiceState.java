package shippingService;

public enum ShippingServiceState {

		//입고대기
		RECEIVE_BOX_READY(1),
		
		//입고완료
		RECEIVE_BOX_COMPLETION(2),
		
		//결제요청
		PAYMENT_READY(3),
		
		//결제완료
		PAYMENT_COMPLETION(4),
		
		//해외배송중
		DELIVERY_WORLD(5),
		
		//통관진행
		IMPORT_CENTER(6),
		
		//국내배송
		DELIVERY_KOREA(7),
		
		//배송완료
		DELIVERYFINISHED(8);
	  
		private final int stateCode;

		ShippingServiceState(int stateCode) {
	        this.stateCode = stateCode;
	    }
	    
	    public int getCode() {
	        return this.stateCode;
	    }
	    
	    public String getShippingStateName(int stateCode) {
	    	String stateName="";
	    	switch(stateCode) {
	    		case 1 : 
	    			stateName = "입고대기";
	    			break;
	    		case 2 : 
	    			stateName = "입고완료";
	    			break;
	    		case 3 : 
	    			stateName = "결제요청";
	    			break;
	    		case 4 : 
	    			stateName = "결제완료";
	    			break;
	    		case 5 : 
	    			stateName = "해외배송중";
	    			break;
	    		case 6 : 
	    			stateName = "통관진행";
	    			break;
	    		case 7 : 
	    			stateName = "국내배송";
	    			break;
	    		case 8 : 
	    			stateName = "배송완료";
	    			break;
	    	}
	    	return stateName;
	    }
}

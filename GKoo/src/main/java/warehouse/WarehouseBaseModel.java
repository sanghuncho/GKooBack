package warehouse;

abstract class WarehouseBaseModel {
	protected String orderNumber;
	protected String memberId;
	
//	protected String getOrderNumber() {
//		return this.orderNumber;
//	}
//	
//	protected String getMemberId() {
//		return this.memberId;
//	}
//	
//	void setOrderNumber(String orderNr) {
//		this.orderNumber = orderNr;
//	}
//	
//	void setMemberId(String memberId) {
//		this.memberId = memberId;
//	}
	
	abstract String getOrderNumber();
	
	abstract String getMemberId();
	
	abstract void setOrderNumber(String orderNr);
	
	abstract void setMemberId(String memberId);
}

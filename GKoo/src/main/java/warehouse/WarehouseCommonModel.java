package warehouse;

public class WarehouseCommonModel {//extends WarehouseBaseModel {
	private String orderNumber;
	private String memberId;
	
	public WarehouseCommonModel() {}

	//@Override
	public String getOrderNumber() {
		return this.orderNumber;
	}

	//@Override
	public String getMemberId() {
		return this.memberId;
	}

	//@Override
	public void setOrderNumber(String orderNr) {
		this.orderNumber = orderNr;
	}

	//@Override
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
}
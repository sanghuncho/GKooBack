package mypage.information;

public class ProductsCommonInformation {
	private String shopUrl;
	private String trackingCompany;
	private String trackingNr;
	private String shipState;
	private double totalPrice;

	public ProductsCommonInformation() {}
	
	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public String getTrackingCompany() {
		return trackingCompany;
	}

	public void setTrackingCompany(String trackingCompany) {
		this.trackingCompany = trackingCompany;
	}

	public String getTrackingNr() {
		return trackingNr;
	}

	public void setTrackingNr(String trackingNr) {
		this.trackingNr = trackingNr;
	}

	public String getShipState() {
		return this.shipState;
	}

	public void setShipState(int shipState) {
		String stateName="";
		switch(shipState) {
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
		this.shipState = stateName;
    }

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}

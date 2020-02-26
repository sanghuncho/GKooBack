package mypage.information;

import java.util.Date;

public class ProductsCommonInformation {
	
    private String shopUrl;
	private String trackingCompany;
	private String trackingNr;
	
	private String shipState;
	private double totalPrice;
	
	private double actualWeight;
	private double volumeWeight;
	private double shipPrice;
	private double shipPriceDiscount;
	
	private int paymentid;
	private Date paymentDate;
	private double paymentDeposit;
	private String paymentOwnerName;
	private int paymentState;
	private int paymentArt;

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

	/*refactoring : return only value, render value to string in frontend*/
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
	
	public double getActualWeight() {
		return actualWeight;
	}

	public void setActualWeight(double actualWeight) {
		this.actualWeight = actualWeight;
	}

	public double getVolumeWeight() {
		return volumeWeight;
	}

	public void setVolumeWeight(double volumeWeight) {
		this.volumeWeight = volumeWeight;
	}

	public double getShipPriceDiscount() {
		return shipPriceDiscount;
	}

	public void setShipPriceDiscount(double shipPriceDiscount) {
		this.shipPriceDiscount = shipPriceDiscount;
	}

	public double getShipPrice() {
		return shipPrice;
	}

	public void setShipPrice(double shipPrice) {
		this.shipPrice = shipPrice;
	}

	public String getPaymentOwnerName() {
		return paymentOwnerName;
	}

	public void setPaymentOwnerName(String paymentOwnerName) {
		this.paymentOwnerName = paymentOwnerName;
	}

	public int getPaymentState() {
		return paymentState;
	}

	public void setPaymentState(int paymentState) {
		this.paymentState = paymentState;
	}

    public int getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(int paymentid) {
        this.paymentid = paymentid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentDeposit() {
        return paymentDeposit;
    }

    public void setPaymentDeposit(double paymentDeposit) {
        this.paymentDeposit = paymentDeposit;
    }

    public void setShipState(String shipState) {
        this.shipState = shipState;
    }

    public int getPaymentArt() {
        return paymentArt;
    }

    public void setPaymentArt(int paymentArt) {
        this.paymentArt = paymentArt;
    }
}

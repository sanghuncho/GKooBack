package shippingService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Configuration;
import payment.PaymentState;

@Configuration
public class ShippingServiceModel {
	
	private String memberId;
	private String timeStamp;
	private String orderId;

	private String easyship;
	private String shopUrl;
	private String trackingCompany;
	private String trackingNumber;
	
	private String receiverNameByKorea;
	private String setOwnerContent;
	private String receiverNameByEnglish;
	
	private String privateTransit;
	private String transitNumber;
	private String agreeWithCollection;
	
	private String callNumberFront;
	private String callNumberMiddle;
	private String callNumberRear;

	private String postCode;
	private String deliveryAddress;
	private String detailAddress;
	private String deliveryMessage;
	
	/** 국제배송비 */
	private double shippingPrice;
	/** 국제배송 상태 */
	private int shipState;
	private int paymentState;

	private ArrayList<ShippingProduct> shippingProductList = new ArrayList<>();
	private double shippingProductspriceSum;
	
	public ArrayList<ShippingProduct> getShippingProductList(){
		/** Todo: immutable guava, return type heap? */
		return shippingProductList;
	}
	public ShippingServiceModel() {}
	
	public void setDeliveryData(DeliveryDataObject data) {
	    this.shopUrl = data.getShopUrl();
	    this.trackingCompany = data.getTrackingTitle();
	    this.trackingNumber = data.getTrackingNumber();
	}
	
	public void setShippingProductsList(ShippingProduct[] shippingProducts) {
	    ArrayList<ShippingProduct> arrayList = new ArrayList<ShippingProduct>(Arrays.asList(shippingProducts));
	    this.shippingProductspriceSum = arrayList.stream().mapToDouble(ShippingProduct::getProductTotalPrice).sum();
	    this.shippingProductList = arrayList;
	}
	
	public String getShopUrl() {
        return shopUrl;
    }
	
    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }
    
    public String getEasyship() {
        return easyship;
    }
    
    public void setEasyship(String easyship) {
        this.easyship = easyship;
    }
    
    public String getTrackingCompany() {
        return trackingCompany;
    }
    
    public void setTrackingCompany(String trackingCompany) {
        this.trackingCompany = trackingCompany;
    }
    
    public String getTrackingNumber() {
        return trackingNumber;
    }
    
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
	
	public String getReceiverNameByKorea() {
		return receiverNameByKorea;
	}
	
	public void setReceiverNameByKorea(String receiverNameByKorea) {
		this.receiverNameByKorea = receiverNameByKorea;
	}
	public String getSetOwnerContent() {
		return setOwnerContent;
	}
	
	public void setOwnerContent(String setOwnerContent) {
		this.setOwnerContent = setOwnerContent;
	}

	public String getReceiverNameByEnglish() {
		return receiverNameByEnglish;
	}
	
	public void setReceiverNameByEnglish(String receiverNameByEnglish) {
		this.receiverNameByEnglish = receiverNameByEnglish;
	}
	
	public String getPrivateTransit() {
		return privateTransit;
	}
	
	public void setPrivateTransit(String privateTransit) {
		this.privateTransit = privateTransit;
	}
	
	public String getTransitNumber() {
		return transitNumber;
	}
	
	public void setTransitNumber(String transitNumber) {
		this.transitNumber = transitNumber;
	}

	public String getAgreeWithCollection() {
		return agreeWithCollection;
	}
	
	public void setAgreeWithCollection(String agreeWithCollection) {
		this.agreeWithCollection = agreeWithCollection;
	}
	
	public String getCallNumberFront() {
		return callNumberFront;
	}
	
	public void setCallNumberFront(String callNumberFront) {
		this.callNumberFront = callNumberFront;
	}

	public String getCallNumberMiddle() {
		return callNumberMiddle;
	}
	
	public void setCallNumberMiddle(String callNumberMiddle) {
		this.callNumberMiddle = callNumberMiddle;
	}

	public String getCallNumberRear() {
		return callNumberRear;
	}
	
	public void setCallNumberRear(String callNumberRear) {
		this.callNumberRear = callNumberRear;
	}
	
	public String getPostCode() {
		return postCode;
	}
	
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDetailAddress() {
		return detailAddress;
	}
	
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getDeliveryMessage() {
		return deliveryMessage;
	}
	
	public void setDeliveryMessage(String deliveryMessage) {
		this.deliveryMessage = deliveryMessage;
	}
	

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public double getShippingPrice() {
		return shippingPrice;
	}
	
	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
	
	public int getShipState() {
		return shipState;
	}
	
	public void setShipState(ShippingServiceState shipState) {
		this.shipState = shipState.getCode();
	}
	
	public int getPaymentState() {
		return paymentState;
	}
	
	public void setPaymentState(PaymentState paymentState) {
		this.paymentState = paymentState.getCode();
	}
	
    public double getShippingProductspriceSum() {
        return shippingProductspriceSum;
    }
}
package shippingService;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ShippingServiceModel {
	
	private String memberId;
	private String timeStamp;
	private String orderId;

	private String shopUrl;
    private String easyship;
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
	
	private double shippingPrice;
	private int shipState;
	private int paymentState;

	private ArrayList<ShippingProduct> shippingProductList = new ArrayList<>();
	
	public ArrayList<ShippingProduct> getShippingProductList(){
		
		/**
	     *Todo: immutable guava, return type heap?
	     */
		return shippingProductList;
	}
	public ShippingServiceModel() {}
	
	/**
     *Todo: implement other list.
     */
	
	//additional product
	private ArrayList<String> shopUrlList = new ArrayList<>(); 

	public void addProduct(HashMap<String, Object>[] data) {
		ShippingProduct product = new ShippingProduct();
	
		product.setCategoryTitle(data[4].get("categoryTitle").toString());
		product.setItemTitle(data[5].get("itemTitle").toString());
		product.setBrandName(data[6].get("brandName").toString());
		product.setItemName(data[7].get("itemName").toString());
		product.setAmount("1");
		product.setPrice("6");
		product.setTotalPrice(data[8].get("totalPrice").toString());
		shippingProductList.add(product);
	}
	
	public void addMoreProducts() {
		for(int i=0; i < shopUrlList.size(); i++ ) {
			ShippingProduct product = new ShippingProduct();
			// other list here added
			shippingProductList.add(product);
		}
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
	
	public void setShopUrlList(ArrayList<String> shopList) {
		this.shopUrlList.addAll(shopList);
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
	
	public void setPaymentState(ShippingServiceState paymentState) {
		this.paymentState = paymentState.getCode();
	}
}
package shippingService;

import java.util.ArrayList;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ShippingServiceModel {
	
	private String shopUrl;
	private String easyship;
	
	private String trackingTitle;
	private String trackingNumber;
	private String categoryTitle;

	private String itemTitle;
	private String brandName;
	private String itemName;
	
	private String totalPrice;
	
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
	
	//additional product
	private ArrayList<String> shopUrlList = new ArrayList<>(); 

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
	
	public String getTrackingTitle() {
		return trackingTitle;
	}
	
	public void setTrackingTitle(String trackingTitle) {
		this.trackingTitle = trackingTitle;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}
	
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	
	public String getCategoryTitle() {
		return categoryTitle;
	}
	
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	
	public String getBrandName() {
		return brandName;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
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

	public String getReceiverNameByEnglishr() {
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
}

package com.gkoo.data;

public class WarehouseInformation {
	private String orderNumber;
	private String productInfo;
	private String recipient;
	private double deliveryPayment;
	private int deliveryState;
	private String deliveryTracking;
	
	public WarehouseInformation(){}
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public double getDeliveryPayment() {
		return deliveryPayment;
	}

	public void setDeliveryPayment(double deliveryPayment) {
		this.deliveryPayment = deliveryPayment;
	}

	public int getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(int deliveryState) {
		this.deliveryState = deliveryState;
	}

	public String getDeliveryTracking() {
		return deliveryTracking;
	}

	public void setDeliveryTracking(String deliveryTracking) {
		this.deliveryTracking = deliveryTracking;
	}
}
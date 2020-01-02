package com.gkoo.data;

import java.util.Date;

/**
 * @author sanghun cho
 *
 */
public class OrderInformation {
	private String orderid;
	private String productInfo;
	private String recipient;
	private double deliveryPayment;
	private int deliveryState;
	private String deliveryTracking;
	private Date orderDate;
	
	public OrderInformation(){}
	
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
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
	
    public Date getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
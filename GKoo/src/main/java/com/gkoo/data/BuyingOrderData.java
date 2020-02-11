package com.gkoo.data;

import java.util.Date;

public class BuyingOrderData {
    private String orderid;
    private String productInfo;
    private double buyingPrice;
    private double deliveryPayment;
    private int deliveryState;
    private String deliveryTracking;
    private Date orderDate;
    
    public BuyingOrderData(){}
    
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

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }
}

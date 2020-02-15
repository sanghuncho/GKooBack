package com.gkoo.data;

import java.util.Date;

public class BuyingOrderData {
    private int objectid;
    private String orderid;
    private String productInfo;
    private double buyingPrice;
    private double deliveryPayment;
    private int buyingServiceState;
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

    public int getBuyingServiceState() {
        return buyingServiceState;
    }

    public void setBuyingServiceState(int buyingServiceState) {
        this.buyingServiceState = buyingServiceState;
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

    public int getObjectid() {
        return objectid;
    }

    public void setObjectid(int objectid) {
        this.objectid = objectid;
    }
}
package com.gkoo.data;

import java.util.Date;

public class PaymentHistoryData {
    private int objectid;
    private Date paymentDate;
    private String orderid;
    private double depositPayment;
    private double buyingPayment;
    private double shippingPayment;
    private String itemname;
    
    //deposit
    private double beignDeposit;
    private double actualDeposit;
    
    public int getObjectid() {
        return objectid;
    }
    
    public void setObjectid(int objectid) {
        this.objectid = objectid;
    }
    
    public Date getPaymentDate() {
        return paymentDate;
    }
    
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public String getOrderid() {
        return orderid;
    }
    
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    
    public double getDepositPayment() {
        return depositPayment;
    }
    
    public void setDepositPayment(double depositPayment) {
        this.depositPayment = depositPayment;
    }
    
    public double getBuyingPayment() {
        return buyingPayment;
    }
    
    public void setBuyingPayment(double buyingPayment) {
        this.buyingPayment = buyingPayment;
    }
    
    public double getShippingPayment() {
        return shippingPayment;
    }
    
    public void setShippingPayment(double shippingPayment) {
        this.shippingPayment = shippingPayment;
    }
    
    public String getItemname() {
        return itemname;
    }
    
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public double getActualDeposit() {
        return actualDeposit;
    }

    public void setActualDeposit(double actualDeposit) {
        this.actualDeposit = actualDeposit;
    }

    public double getBeignDeposit() {
        return beignDeposit;
    }

    public void setBeignDeposit(double beignDeposit) {
        this.beignDeposit = beignDeposit;
    }
}
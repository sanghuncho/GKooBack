package com.gkoo.data.buyingservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.gkoo.data.OptionalServices;
import com.gkoo.data.RecipientData;
import com.gkoo.enums.BuyingServiceState;
import payment.PaymentState;
import shippingService.DeliveryDataObject;

/**
 *
 * @author sanghuncho
 *
 * @since  09.01.2020
 *
 */
@Component("buyingServiceModel")
@Scope(value="prototype")
public class BuyingServiceModel {
    
    private String userid;
    private String orderId;
    private LocalDate orderDate;

    private String shopUrl;
    private String trackingCompany;
    private String trackingNumber;
    
    private RecipientData recipientData;
    
    private double buyingPrice;
    private OptionalServices optionalServices;
    
    /** 국제배송비 */
    private double shippingPrice;
    
    /** 구매대행 상태 */
    private int buyingState;
    
    private int paymentState;

    private ArrayList<BuyingProduct> buyingProductList = new ArrayList<>();
    private double buyingProductspriceSum;
    
    public ArrayList<BuyingProduct> getBuyingProductList(){
        /** Todo: immutable guava, return type heap? */
        return buyingProductList;
    }
        
    public void setDeliveryData(DeliveryDataObject data) {
        this.shopUrl = data.getShopUrl();
        this.trackingCompany = data.getTrackingTitle();
        this.trackingNumber = data.getTrackingNumber();
    }
    
    public void setBuyingProductsList(BuyingProduct[] buyingProducts) {
        ArrayList<BuyingProduct> arrayList = new ArrayList<BuyingProduct>(Arrays.asList(buyingProducts));
        this.buyingProductspriceSum = arrayList.stream().mapToDouble(BuyingProduct::getProductTotalPrice).sum();
        this.buyingProductList = arrayList;
    }
    
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
    
    public String getTrackingNumber() {
        return trackingNumber;
    }
    
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }    

    public String getUserid() {
        return userid;
    }

    public void setUserid(String memberId) {
        this.userid = memberId;
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
    
    public int getBuyingState() {
        return buyingState;
    }
    
    public void setBuyingState(BuyingServiceState buyingState) {
        this.buyingState = buyingState.getCode();
    }
    
    public int getPaymentState() {
        return paymentState;
    }
    
    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState.getCode();
    }
    
    public double getBuyingProductspriceSum() {
        return buyingProductspriceSum;
    }
    
    public java.sql.Date getOrderDate() {
        return java.sql.Date.valueOf(orderDate);
    }
    
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public RecipientData getRecipientData() {
        return recipientData;
    }

    public void setRecipientData(RecipientData recipientData) {
        this.recipientData = recipientData;
    }
}
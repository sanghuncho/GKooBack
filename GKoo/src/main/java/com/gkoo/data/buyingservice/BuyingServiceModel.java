package com.gkoo.data.buyingservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import com.gkoo.data.OptionalServices;
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
public class BuyingServiceModel {
    
    private String userid;
    private String timeStamp;
    private String orderId;
    private LocalDate orderDate;

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
    
    private String phonenumberFirst;
    private String phonenumberSecond;

    private String postCode;
    private String deliveryAddress;
    private String deliveryMessage;
    
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

    public String getDeliveryMessage() {
        return deliveryMessage;
    }
    
    public void setDeliveryMessage(String deliveryMessage) {
        this.deliveryMessage = deliveryMessage;
    }
    

    public String getUserid() {
        return userid;
    }

    public void setUserid(String memberId) {
        this.userid = memberId;
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
    
    public String getPhonenumberSecond() {
        return phonenumberSecond;
    }
    
    public void setPhonenumberSecond(String phonenumberSecond) {
        this.phonenumberSecond = phonenumberSecond;
    }
    
    public String getPhonenumberFirst() {
        return phonenumberFirst;
    }
    
    public void setPhonenumberFirst(String phonenumberFirst) {
        this.phonenumberFirst = phonenumberFirst;
    }
    
    public java.sql.Date getOrderDate() {
        return java.sql.Date.valueOf(orderDate);
    }
    
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}

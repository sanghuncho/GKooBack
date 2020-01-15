package com.gkoo.data.buyingservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import com.gkoo.data.OptionalServices;
import com.gkoo.enums.BuyingServicePaymentState;
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
    private String orderid;
    private LocalDate orderDate;

    private String shopUrl;
    private String trackingCompanyWorld;
    private String trackingNumberWorld;
    private String trackingCompanyKor;
    private String trackingNumberKor;
    
    private String receiverNameByKorea;
    private String receiverNameByEnglish;
    
    private String privateTransit;
    private String transitNumber;
    
    private String phonenumberFirst;
    private String phonenumberSecond;

    private String postCode;
    private String deliveryAddress;
    private String deliveryMessage;
    
    /** 구매대행 견적, 배송비 제외 */
    private double buyingPrice;
    
    /** 국제배송비 */
    private double shippingPrice;
    private double boxActualWeight;
    private double boxVolumeWeight;
    
    private OptionalServices optionalServices;
    
    /** 구매대행 상태 */
    private BuyingServiceState buyingState;
    
    private BuyingServicePaymentState buyingServicePaymentState;

    private ArrayList<BuyingProduct> buyingProductList = new ArrayList<>();
    private double buyingProductsPriceSum;
    
    public ArrayList<BuyingProduct> getBuyingProductList(){
        /** Todo: immutable guava, return type heap? */
        return buyingProductList;
    }
    
    public void setBuyingProductsList(BuyingProduct[] buyingProducts) {
        ArrayList<BuyingProduct> arrayList = new ArrayList<BuyingProduct>(Arrays.asList(buyingProducts));
        this.buyingProductsPriceSum = arrayList.stream().mapToDouble(BuyingProduct::getProductTotalPrice).sum();
        this.buyingProductList = arrayList;
    }
    
    public String getShopUrl() {
        return shopUrl;
    }
    
    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }
    
    public String getReceiverNameByKorea() {
        return receiverNameByKorea;
    }
    
    public void setReceiverNameByKorea(String receiverNameByKorea) {
        this.receiverNameByKorea = receiverNameByKorea;
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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderId) {
        this.orderid = orderId;
    }
    
    public double getShippingPrice() {
        return shippingPrice;
    }
    
    public void setShippingPrice(double shippingPrice) {
        this.shippingPrice = shippingPrice;
    }
    
    public BuyingServiceState getBuyingState() {
        return buyingState;
    }
    
    public void setBuyingState(BuyingServiceState buyingState) {
        this.buyingState = buyingState;
    }
    
    public BuyingServicePaymentState getBuyingServicePaymentState() {
        return buyingServicePaymentState;
    }
    
    public void setBuyingServicePaymentState(BuyingServicePaymentState buyingServicePaymentState) {
        this.buyingServicePaymentState = buyingServicePaymentState;
    }
    
    public double getBuyingProductsPriceSum() {
        return buyingProductsPriceSum;
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

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public OptionalServices getOptionalServices() {
        return optionalServices;
    }

    public void setOptionalServices(OptionalServices optionalServices) {
        this.optionalServices = optionalServices;
    }

    public String getTrackingCompanyWorld() {
        return trackingCompanyWorld;
    }

    public void setTrackingCompanyWorld(String trackingCompanyWorld) {
        this.trackingCompanyWorld = trackingCompanyWorld;
    }

    public String getTrackingNumberWorld() {
        return trackingNumberWorld;
    }

    public void setTrackingNumberWorld(String trackingNumberWorld) {
        this.trackingNumberWorld = trackingNumberWorld;
    }

    public String getTrackingCompanyKor() {
        return trackingCompanyKor;
    }

    public void setTrackingCompanyKor(String trackingCompanyKor) {
        this.trackingCompanyKor = trackingCompanyKor;
    }

    public String getTrackingNumberKor() {
        return trackingNumberKor;
    }

    public void setTrackingNumberKor(String trackingNumberKor) {
        this.trackingNumberKor = trackingNumberKor;
    }

    public double getBoxActualWeight() {
        return boxActualWeight;
    }

    public void setBoxActualWeight(double boxActualWeight) {
        this.boxActualWeight = boxActualWeight;
    }

    public double getBoxVolumeWeight() {
        return boxVolumeWeight;
    }

    public void setBoxVolumeWeight(double boxVolumeWeight) {
        this.boxVolumeWeight = boxVolumeWeight;
    }
}

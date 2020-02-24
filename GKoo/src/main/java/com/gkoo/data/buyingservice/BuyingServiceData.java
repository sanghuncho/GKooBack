package com.gkoo.data.buyingservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.gkoo.data.OptionalServices;
import com.gkoo.data.RecipientData;
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
@Component("buyingServiceData")
@Scope(value="prototype")
public class BuyingServiceData {
    private int buyingServiceid;
    private String userid;
    private String orderid;
    private LocalDate orderDate;

    private String shopUrl;
    private String trackingCompanyWorld;
    private String trackingNumberWorld;
    private String trackingCompanyKor;
    private String trackingNumberKor;
    
    private RecipientData recipientData;
    
    /** 구매대행 견적, 배송비 제외 */
    private double buyingPrice;
    
    /** 해외 현지 배송비*/
    private double shopDeliveryPrice;
    
    /** 국제배송비 */
    private double shippingPrice;
    private double boxActualWeight;
    private double boxVolumeWeight;
    
    private OptionalServices optionalServices;
    
    /** 구매대행 상태 */
    private BuyingServiceState buyingState;
    
    private BuyingServicePaymentState buyingServicePaymentState;

    private ArrayList<BuyingProduct> buyingProductList = new ArrayList<>();
    private double productsListTotalPrice;
    
    public BuyingServiceData() {}
    
    public ArrayList<BuyingProduct> getBuyingProductList(){
        /** Todo: immutable guava, return type heap? */
        return buyingProductList;
    }
    
    public void setBuyingProductsList(BuyingProduct[] buyingProducts) {
        ArrayList<BuyingProduct> arrayList = new ArrayList<BuyingProduct>(Arrays.asList(buyingProducts));
        this.productsListTotalPrice = arrayList.stream().mapToDouble(BuyingProduct::getProductTotalPrice).sum();
        this.buyingProductList = arrayList;
    }
    
    public String getShopUrl() {
        return shopUrl;
    }
    
    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }
    
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
    
    public double getProductsListTotalPrice() {
        return productsListTotalPrice;
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

    public int getBuyingServiceid() {
        return buyingServiceid;
    }

    public void setBuyingServiceid(int buyingServiceid) {
        this.buyingServiceid = buyingServiceid;
    }

    public double getShopDeliveryPrice() {
        return shopDeliveryPrice;
    }

    public void setShopDeliveryPrice(double shopDeliveryPrice) {
        this.shopDeliveryPrice = shopDeliveryPrice;
    }
}
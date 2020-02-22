package com.gkoo.data;

import java.util.List;
import mypage.information.ProductsInformation.Product;
import payment.PaymentData;

public class BuyingServiceDetailData {
    private RecipientData recipientData;
    private int buyingServiceState;
    private PaymentData productPayment;
    private PaymentData deliveryPayment;
    private List<Product> productsList;
        
    public BuyingServiceDetailData(RecipientData recipientData, PaymentData productPayment, PaymentData deliveryPayment, List<Product> productsList) {
        this.recipientData = recipientData;
    }

    public RecipientData getRecipientData() {
        return recipientData;
    }

    public void setRecipientData(RecipientData recipientData) {
        this.recipientData = recipientData;
    }

    public int getBuyingServiceState() {
        return buyingServiceState;
    }

    public PaymentData getProductPayment() {
        return productPayment;
    }

    public PaymentData getDeliveryPayment() {
        return deliveryPayment;
    }

    public List<Product> getProductsList() {
        return productsList;
    }
}
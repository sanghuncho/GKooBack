package com.gkoo.data;

import java.util.List;
import mypage.information.ProductsInformation.Product;
import payment.PaymentData;

/**
 * @author sanghuncho
 *
 */
public class BuyingServiceDetailData {
    private CustomerData customerData;
    private RecipientData recipientData;
    private int buyingServiceState;
    private PaymentData productPayment;
    private PaymentData deliveryPayment;
    private DeliveryKoreaData deliveryKoreaData;
    private List<Product> productsInfo;
    private BuyingServiceCommonData buyingServiceCommonData;
        
    public BuyingServiceDetailData(RecipientData recipientData, PaymentData productPayment, 
                PaymentData deliveryPayment, DeliveryKoreaData deliveryKoreaData, List<Product> productsInfo,
                BuyingServiceCommonData buyingServiceCommonData, CustomerData customerData) {
        this.recipientData = recipientData;
        this.productPayment = productPayment;
        this.buyingServiceState = productPayment.getBuyingServiceState();
        this.deliveryPayment = deliveryPayment;
        this.deliveryKoreaData = deliveryKoreaData;
        this.productsInfo = productsInfo;
        this.buyingServiceCommonData = buyingServiceCommonData;
        this.setCustomerData(customerData);
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

    public DeliveryKoreaData getDeliveryKoreaData() {
        return deliveryKoreaData;
    }

    public void setDeliveryKoreaData(DeliveryKoreaData deliveryKoreaData) {
        this.deliveryKoreaData = deliveryKoreaData;
    }

    public List<Product> getProductsInfo() {
        return productsInfo;
    }

    public BuyingServiceCommonData getBuyingServiceCommonData() {
        return buyingServiceCommonData;
    }

    public void setBuyingServiceCommonData(BuyingServiceCommonData buyingServiceCommonData) {
        this.buyingServiceCommonData = buyingServiceCommonData;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }
}
package mypage;

import com.gkoo.data.CustomerData;
import com.gkoo.data.RecipientData;
import mypage.information.ProductsCommonInformation;

public class MypageDetailData {
    private CustomerData customerData;
    private RecipientData recipientData;
    private ProductsCommonInformation productsCommonInformation;
    
    public MypageDetailData(RecipientData recipientData, ProductsCommonInformation productsCommonInformation, CustomerData customerData) {
        this.recipientData = recipientData;
        this.productsCommonInformation = productsCommonInformation;
        this.setCustomerData(customerData);
    }

    public RecipientData getRecipientData() {
        return recipientData;
    }

    public void setRecipientData(RecipientData recipientData) {
        this.recipientData = recipientData;
    }

    public ProductsCommonInformation getProductsCommonInformation() {
        return productsCommonInformation;
    }

    public void setProductsCommonInformation(ProductsCommonInformation productsCommonInformation) {
        this.productsCommonInformation = productsCommonInformation;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }
}
package mypage;

import mypage.information.ProductsCommonInformation;
import mypage.information.RecipientData;

public class MypageDetailData {
    private RecipientData recipientData;
    private ProductsCommonInformation productsCommonInformation;
    
    public MypageDetailData(RecipientData recipientData, ProductsCommonInformation productsCommonInformation) {
        this.recipientData = recipientData;
        this.productsCommonInformation = productsCommonInformation; 
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

    
    
    
}
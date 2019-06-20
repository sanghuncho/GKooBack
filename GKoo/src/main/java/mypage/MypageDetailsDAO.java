package mypage;

import java.util.List;

import mypage.information.ProductsCommonInformation;
import mypage.information.RecipientInformation;
import mypage.information.ProductsInformation.Product;

public interface MypageDetailsDAO {
	public OrderingPersonInformation getOrderingpersonInfo(String fullname);
	
	public RecipientInformation getRecipientInfo(String username, String number);
	
	public List<Product> getProductsInfo(String username, String number);
	
	public ProductsCommonInformation getProductsCommonInfo(String username, String number);
}

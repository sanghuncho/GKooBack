package mypage;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.ResponseEntity;
import mypage.information.ProductsCommonInformation;
import mypage.information.RecipientData;
import mypage.information.ProductsInformation.Product;

public interface MypageDetailsDAO {
	public OrderingPersonInformation getOrderingpersonInfo(String fullname);
	
	public RecipientData getRecipientInfo(String username, String number);
	
	public List<Product> getProductsInfo(String username, String number);
	
	public ProductsCommonInformation getProductsCommonInfo(String username, String number);
	
	public void willPayDeliveryFee(String username, String orderNumber, String ownerName);
		
	public ResponseEntity<?> updateRecipientData(String memberId, HashMap<String, Object>[] data);
}

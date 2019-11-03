package mypage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
	
	public ResponseEntity<?> updateDataEditorProductsList(String memberId, HashMap<String, Object>[] data) throws JsonParseException, JsonMappingException, IOException, SQLException;
	
	public ResponseEntity<?> deleteShipingServiceData(String memberId, HashMap<String, Object>[] data) throws JsonParseException, JsonMappingException, IOException, SQLException;
}

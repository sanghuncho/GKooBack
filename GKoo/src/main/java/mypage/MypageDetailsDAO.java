package mypage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gkoo.data.BuyingServiceCommonData;
import com.gkoo.data.BuyingServiceDetailData;
import com.gkoo.data.RecipientData;
import mypage.information.ProductsCommonInformation;
import mypage.information.ProductsInformation;
import mypage.information.ProductsInformation.Product;
import payment.PaymentData;

/**
 * @author sanghuncho
 *
 */
public interface MypageDetailsDAO {
	public OrderingPersonInformation getOrderingpersonInfo(String fullname);
	public RecipientData getRecipientInfo(String username, String number);
	public List<Product> getProductsInfo(String username, String number);
	public ProductsCommonInformation getProductsCommonInfo(String username, String number);
	public MypageDetailData getMypageDetailData(String userid, String orderid);
	public void willPayDeliveryFee(String username, String orderNumber, String ownerName);
	public ResponseEntity<?> updateRecipientData(String memberId, HashMap<String, Object>[] data);
	public ResponseEntity<?> updateDataEditorProductsList(String memberId, HashMap<String, Object>[] data) throws JsonParseException, JsonMappingException, IOException, SQLException;
	public ResponseEntity<?> deleteShipingServiceData(String memberId, HashMap<String, Object>[] data) throws JsonParseException, JsonMappingException, IOException, SQLException;
	
    /////////////////////
    /// BuyingService ///
    /////////////////////
	public BuyingServiceDetailData getMypageBuyingServiceDetailData(String userid, String orderid);
	public RecipientData getRecipientBuyingService(String userid, String orderid);
	public PaymentData getPaymentProductBuyingServiceByOrderid(String orderid);
	public PaymentData getPaymentDeliveryBuyingServiceByOrderid(String orderid);
	public List<Product> getProductDataBuyingService(String userid, String orderid);
	public BuyingServiceCommonData getBuyingServiceCommonData(String orderid);
	public RecipientData getRecipientDataBuyingService(String userid, String orderid);
}
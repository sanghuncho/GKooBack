package mypage;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.gkoo.data.DeliveryKoreaData;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import payment.PaymentData;

public interface OverviewServiceDAO {
	
	public List<OrderInformation> getOrderInformationFromDB(String username);
	
	public List<WarehouseInformation> getWarehouseInformationFromDB(String username);
	
	public ResponseEntity<?> updateTrackingNumber(String memberId, String orderNumber, String trackingCompany,String trackngNumber);
	
	public List<PaymentData> getPaymentData(String userid);
	
	public List<DeliveryKoreaData> getDeliveryKoreaData(String userid);
	
}
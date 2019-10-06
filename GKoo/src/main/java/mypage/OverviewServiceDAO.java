package mypage;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface OverviewServiceDAO {
	
	public List<OrderInformation> getOrderInformationFromDB(String username);
	
	public List<WarehouseInformation> getWarehouseInformationFromDB(String username);
	
	public ResponseEntity<?> updateTrackingNumber(String memberId, String orderNumber, String trackingCompany,String trackngNumber);
}
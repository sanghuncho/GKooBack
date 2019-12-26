package mypage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gkoo.configuration.SecurityConfig;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import serviceBase.ServicePath;
import util.AuthentificationService;

@RestController
public class OverviewServiceController {
	public OverviewServiceController() {}
	
	//autowired OverviewInformationImpl?
	
	@CrossOrigin(origins = ServicePath.MYPAGE)
	@RequestMapping("/orderinformation")
	public List<OrderInformation> requestOrderInformation(HttpServletRequest request) throws SQLException  {
		OverviewInformationImpl overviewImp = new OverviewInformationImpl();
        String userid = SecurityConfig.getUserid(request);      
		return overviewImp.getOrderInformationFromDB(userid);
	}
	
	// ToDo : Refactoring 
	@CrossOrigin(origins = ServicePath.MYPAGE)
	@RequestMapping("/warehouseinformation")
	public List<WarehouseInformation> requestWarehouseInformation(HttpServletRequest request) throws SQLException  {
		OverviewInformationImpl overviewImp = new OverviewInformationImpl();
		String memberId = AuthentificationService.getAuthenficatedMemberID(request);
		/*ToDo : low coupling - interface, injection */
		return overviewImp.getWarehouseInformationFromDB(memberId);
	}
	
	@CrossOrigin(origins = ServicePath.MYPAGE)
	@RequestMapping(value = "/updatetrackingnumber", method = RequestMethod.POST)
	public ResponseEntity<?> updateTrackingNumber(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException  {
		OverviewInformationImpl overviewImpl = new OverviewInformationImpl();
		String memberId = AuthentificationService.getAuthenficatedMemberID(request);
		String orderNumber = data[0].get("orderNumber").toString();
		String trackingCompany = data[1].get("trackingCompany").toString();
	    String trackingNumber = data[2].get("trackingNumber").toString();
		return overviewImpl.updateTrackingNumber(memberId, orderNumber, trackingCompany, trackingNumber);
	}
}

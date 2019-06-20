package mypage;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import util.AuthentificationService;

@RestController
public class OverviewServiceController {
	public OverviewServiceController() {}
	
	@CrossOrigin(origins = "http://localhost:3000/mypage")
	@RequestMapping("/orderinformation")
	public List<OrderInformation> requestOrderInformation(HttpServletRequest request) throws SQLException  {
		OverviewInformationImpl overviewImp = new OverviewInformationImpl();
		String memberId = AuthentificationService.getAuthenficatedMemberID(request);
		/*ToDo : low coupling - Spring injection, interface, injection */
		return overviewImp.getOrderInformationFromDB(memberId);
	}
	
	// ToDo : Refactoring 
	@CrossOrigin(origins = "http://localhost:3000/mypage")
	@RequestMapping("/warehouseinformation")
	public List<WarehouseInformation> requestWarehouseInformation(HttpServletRequest request) throws SQLException  {
		OverviewInformationImpl overviewImp = new OverviewInformationImpl();
		String memberId = AuthentificationService.getAuthenficatedMemberID(request);
		/*ToDo : low coupling - interface, injection */
		return overviewImp.getWarehouseInformationFromDB(memberId);
	}
	
	// other impl of informations 

}
package mypage;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import util.AuthentificationService;

@RestController
public class OrderInformationController {
	public OrderInformationController() {}
	
	@CrossOrigin(origins = "http://localhost:3000/mypage")
	@RequestMapping("/orderinformation")
	public List<OrderInformation> requestOrderInformation(HttpServletRequest request) throws SQLException  {
		OrderInformationImp orderDAO = new OrderInformationImp();
		String memberId = AuthentificationService.getAuthenficatedMemberID(request);
		/*ToDo : low coupling - interface, injection */
		return orderDAO.getOrderInformationFromDB(memberId);
	}
	
	@CrossOrigin(origins = "http://localhost:3000/mypage")
	@RequestMapping("/warehouseinformation")
	public List<OrderInformation> requestwarehouseInformation(HttpServletRequest request) throws SQLException  {
		OrderInformationImp orderDAO = new OrderInformationImp();
		String memberId = AuthentificationService.getAuthenficatedMemberID(request);
		/*ToDo : low coupling - interface, injection */
		return orderDAO.getWarehouseInformationFromDB(memberId);
	}

}

package mypage;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerStatusController {
	
	public CustomerStatusController(){}

	@CrossOrigin(origins = "http://localhost:3000/mypage")
	@RequestMapping("/customerstatus")
	public CustomerStatus requestCustomerStatus() throws SQLException {		
		CustomerStatusDAO statusDAO = new CustomerStatusDAO();
		return statusDAO.getCustomerStatusFromDB();
	}
}

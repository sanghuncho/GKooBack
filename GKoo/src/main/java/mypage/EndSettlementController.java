package mypage;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndSettlementController {

	public EndSettlementController() {}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/endSettlementList")
	public List<EndSettlement> requestEndSettlementMoney() throws SQLException {		
		EndSettlementDAO settlementDAO = new EndSettlementDAO();
		
		//return settlementDAO.getSettlementListFromLocal();
		return settlementDAO.getSettlementListFromDB();
	}
}

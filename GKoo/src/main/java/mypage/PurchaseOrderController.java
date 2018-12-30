package mypage;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseOrderController {

    public PurchaseOrderController(){}
    
    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/purchaseOderList")
	public List<PurchaseOrder> requestpurchaseOderList() throws SQLException {		
    	PurchaseOrderDAO purchaseOrderList = new PurchaseOrderDAO();
		
		//return settlementDAO.getSettlementListFromLocal();
		return purchaseOrderList.getPurchaseOrderListFromDB();
	}
}

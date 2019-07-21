package managementService;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serviceBase.ServicePath;
import warehouse.WarehouseCommonModel;
import warehouse.WarehouseImp;

@RestController
public class ManagementController {
	
	@CrossOrigin(origins = ServicePath.MANAGEMENT)
	@RequestMapping("/warehousecommonstates")
	public List<WarehouseCommonModel> requestWarehouseCommonStates(HttpServletRequest request) throws SQLException  {
		WarehouseImp warehouseImp = new WarehouseImp();
		return warehouseImp.getWarehouseCommonStates();
	}
}

package mypage;

import java.util.List;

public interface OverviewServiceDAO {
	public List<OrderInformation> getOrderInformationFromDB(String username);
	
	public List<WarehouseInformation> getWarehouseInformationFromDB(String username);
}
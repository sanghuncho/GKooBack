package mypage;

import java.util.List;

public interface OrderInformationDAO {
	public List<OrderInformation> getOrderInformationFromDB(String username);
	
	public List<OrderInformation> getWarehouseInformationFromDB(String username);
}

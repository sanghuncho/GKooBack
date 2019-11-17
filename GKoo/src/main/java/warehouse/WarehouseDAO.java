package warehouse;

import java.util.List;
import com.gkoo.data.WarehouseInformation;

public interface WarehouseDAO {

	public List<WarehouseInformation> getWarehouseCustomState(String username);
	
	public List<WarehouseCommonModel> getWarehouseCommonStates();
}

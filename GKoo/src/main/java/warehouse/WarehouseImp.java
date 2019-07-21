package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import databaseUtil.ConnectionDB;
import mypage.WarehouseInformation;

public class WarehouseImp implements WarehouseDAO {

	@Override
	public List<WarehouseInformation> getWarehouseCustomState(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WarehouseCommonModel> getWarehouseCommonStates() {
		ResultSet resultSet = null;
		ConnectionDB.connectSQL();
		String query = "SELECT os.orderid, os.memberid FROM ORDERSTATE os WHERE os.ship_state=1 or os.ship_state=2";
				
		List<WarehouseCommonModel> warehouseCommonModelList = new ArrayList<>();
		try (Connection conn = ConnectionDB.getConnectInstance();
				PreparedStatement psmt = conn.prepareStatement(query);){
			resultSet = psmt.executeQuery();
			warehouseCommonModelList = writeWarehouseCommonStates(resultSet, warehouseCommonModelList);
		} catch (SQLException e) {
			//Logger
		}
		
		return warehouseCommonModelList;
	}

	private List<WarehouseCommonModel> writeWarehouseCommonStates(ResultSet rs, List<WarehouseCommonModel> warehouseCommonModelList) throws SQLException {
		while (rs.next()) {
			WarehouseCommonModel warehouseCommon = new WarehouseCommonModel();
			warehouseCommon.setOrderNumber(rs.getString("orderid"));
			warehouseCommon.setMemberId(rs.getString("memberId"));
			
			warehouseCommonModelList.add(warehouseCommon);
		}
		return warehouseCommonModelList;
	}
}

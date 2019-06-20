package mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dataBase.ConnectionDB;

public class OverviewInformationImpl implements OverviewServiceDAO {
    
	public OverviewInformationImpl(){}
	
	@Override
	public List<OrderInformation> getOrderInformationFromDB(String username) {
		ResultSet resultSet = null;
		ConnectionDB.connectSQL();
		String query = "SELECT os.orderid, os.ship_price, os.ship_state, os.trackingnr_kor, os.trackingnr_world, rp.name_kor "
				+ "	FROM ORDERSTATE os, RECIPIENT rp WHERE rp.orderid=os.orderid AND os.memberid=?";
				
		List<OrderInformation> orderInformationList = new ArrayList<>();
		try (Connection conn = ConnectionDB.getConnectInstance();
				PreparedStatement psmt = conn.prepareStatement(query);){
			psmt.setString(1, username);
			resultSet = psmt.executeQuery();
			orderInformationList = writeOrderInformation(resultSet, orderInformationList);
		} catch (SQLException e) {
			//Logger
		}
			
		return orderInformationList;
	}
	
	@Override
	public List<WarehouseInformation> getWarehouseInformationFromDB(String username) {
		ResultSet resultSet = null;
		ConnectionDB.connectSQL();
		String query = "SELECT os.orderid, os.ship_price, os.ship_state, os.trackingnr_kor, os.trackingnr_world, rp.name_kor "
				+ "	FROM ORDERSTATE os, RECIPIENT rp WHERE rp.orderid=os.orderid AND os.memberid=? AND (os.ship_state=1 or os.ship_state=2)";
				
		List<WarehouseInformation> warehouseInformationList = new ArrayList<>();
		try (Connection conn = ConnectionDB.getConnectInstance();
				PreparedStatement psmt = conn.prepareStatement(query);){
			psmt.setString(1, username);
			resultSet = psmt.executeQuery();
			warehouseInformationList = writeWarehouseInformation(resultSet, warehouseInformationList);
		} catch (SQLException e) {
			//Logger
		}
		
		return warehouseInformationList;
	}
	
	private List<OrderInformation> writeOrderInformation(ResultSet rs, List<OrderInformation> orderInformationList) throws SQLException {
		while (rs.next()) {
			OrderInformation orderInfo = new OrderInformation();
			orderInfo.setOrderNumber(rs.getString("orderid"));
			orderInfo.setProductInfo(collectProductInfos(orderInfo.getOrderNumber()));
			orderInfo.setRecipient(rs.getString("name_kor"));
			orderInfo.setDeliveryPayment(rs.getDouble("ship_price"));
			orderInfo.setDeliveryState(rs.getInt("ship_state"));
			//ToDo: depends on the local, new impl
			orderInfo.setDeliveryTracking(rs.getString("trackingnr_world"));
			orderInformationList.add(orderInfo);
		}
		return orderInformationList;
	}
	
	private List<WarehouseInformation> writeWarehouseInformation(ResultSet rs, List<WarehouseInformation> warehouseInformationList) throws SQLException {
		while (rs.next()) {
			WarehouseInformation warehouseInfo = new WarehouseInformation();
			warehouseInfo.setOrderNumber(rs.getString("orderid"));
			warehouseInfo.setProductInfo(collectProductInfos(warehouseInfo.getOrderNumber()));
			warehouseInfo.setRecipient(rs.getString("name_kor"));
			warehouseInfo.setDeliveryPayment(rs.getDouble("ship_price"));
			warehouseInfo.setDeliveryState(rs.getInt("ship_state"));
			//ToDo: depends on the local, new impl
			warehouseInfo.setDeliveryTracking(rs.getString("trackingnr_world"));
			warehouseInformationList.add(warehouseInfo);
		}
		return warehouseInformationList;
	}
	
	private String collectProductInfos(String orderId) {
		List<String> products = new ArrayList<>();
		ResultSet resultSet = null;
		
		final String GET_PRODUCTS_NAME = "SELECT pd_itemtitle FROM PRODUCT WHERE orderid=?";
		
		ConnectionDB.connectSQL();
		try (Connection conn = ConnectionDB.getConnectInstance();
				PreparedStatement psmt = conn.prepareStatement(GET_PRODUCTS_NAME);){
			psmt.setString(1, orderId);
			resultSet = psmt.executeQuery();
			while (resultSet.next()) {
				products.add(resultSet.getString("pd_itemtitle"));
			}
		} catch (SQLException e) {
			//Logger
		}

		return products.toString().replace("[", "").replace("]", "");
	}
}
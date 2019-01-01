package mypage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataBase.ConnectionDB;

public class PurchaseOrderDAO {
	private Statement statement = null;
    private ResultSet resultSet = null;
    private Connection conn;
    
    public PurchaseOrderDAO() {}
	
	public List<PurchaseOrder> getPurchaseOrderListFromDB() throws SQLException{
		ConnectionDB.connectSQL();
		Connection conn = ConnectionDB.getConnectInstance();
		
		List<PurchaseOrder> purchaseOrderList = null;
		try {
			this.statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			resultSet = this.statement.executeQuery("select * from purchaseorder");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			purchaseOrderList = writeResultPurchaseOrderListSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return purchaseOrderList;
	}
	
	private List<PurchaseOrder> writeResultPurchaseOrderListSet(ResultSet rs) throws SQLException {
		List<PurchaseOrder> orderList =  new ArrayList<PurchaseOrder>();
		
		while (rs.next()) {
			PurchaseOrder order = new PurchaseOrder();
			
			order.setGkooId(rs.getString("gkooid"));
			
			order.setProductName(rs.getString("productname"));
			
			/*상품아이디와 상품url을 같게 설정함*/
			order.setProductImageUrl(ImageTransform.getImageBase64(rs.getString("gkooid")));
			
			order.setProductPrice(rs.getInt("productprice"));
			
			order.setServiceFee(rs.getInt("servicefee"));
			
			order.setTotalPrice(rs.getInt("totalprice"));
			
			order.setStatus(rs.getString("delieverstatus"));
			
			orderList.add(order);
		}
        return orderList;
    }
	
	private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
    }
}

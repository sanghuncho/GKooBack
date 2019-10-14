package shippingService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import databaseUtil.ConnectionDB;
import util.TimeStamp;

public class ShippingServiceDAO {
	
	private PreparedStatement psmt = null;
    private Connection conn;
    
	public ShippingServiceDAO() {}
	
	public void createShippingServiceDB(ShippingServiceModel model) throws SQLException {
		ConnectionDB.connectSQL();
		conn = ConnectionDB.getConnectInstance();
		
//		memberId varchar(50) not null,
//		orderId varchar(50) not null,
//		name_kor varchar(50) not null,
//		name_eng varchar(50) not null,
//		transit_nr varchar(50) not null,
//		phone_prefic integer,
//		phone_interfix integer,
//		phone_suffix integer,
//		zip_code integer,
//		address text,
//		detail_adress text,
//		userComment text
		
		try {
			PreparedStatement psmt = conn.prepareStatement(CREATE_SHIPPING_SERVICE);
			psmt.setString(1, model.getMemberId());
			psmt.setString(2, String.valueOf(model.getOrderId()));
			psmt.setString(3, model.getReceiverNameByKorea());
			psmt.setString(4, model.getReceiverNameByEnglish());
			psmt.setString(5, model.getTransitNumber());
			
			/**
		     *ToDO: optimization for data type
		     */
			
			psmt.setString(6, model.getCallNumberFront());
			psmt.setString(7, model.getCallNumberMiddle());
			psmt.setString(8, model.getCallNumberRear());
			psmt.setString(9, model.getPostCode());
			psmt.setString(10, model.getDeliveryAddress());
			psmt.setString(11, model.getDetailAddress());
			psmt.setString(12, model.getDeliveryMessage());
	
			psmt.executeUpdate();
		} catch (SQLException e) {
			
		} finally {
			 try {
		            if (psmt != null) {
		            	psmt.close();
		            }
		        } catch (Exception e) {

		        }
		}
		
		try {
			PreparedStatement psmt = conn.prepareStatement(CREATE_SHIPPING_ORDER_STATE);
			psmt.setString(1, model.getMemberId());
			psmt.setString(2, String.valueOf(model.getOrderId()));
			psmt.setDouble(3, model.getShippingPrice());
			psmt.setInt(4, model.getPaymentState());
			psmt.setInt(5, model.getShipState());
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			//Loger
		} finally {
			 try {
		            if (psmt != null) {
		            	psmt.close();
		            }
		        } catch (Exception e) {

		        }
		}

		
//		  memberid character varying(50) NOT NULL,
//		  orderid character varying(50) NOT NULL,
//		  order_stamp timestamp without time zone NOT NULL,
//		  pd_shopurl text,
//		  pd_trackingtitle character varying(50),
//		  pd_trackingnumber character varying(50),
//		  pd_categorytitle character varying(50),
//		  pd_itemtitle character varying(50),
//		  pd_brandname character varying(50),
//		  pd_itemname character varying(50),
//		  pd_amount integer NOT NULL,
//		  pd_price numeric NOT NULL,
//		  pd_totalprice numeric NOT NULL,
		ArrayList<ShippingProduct> products = model.getShippingProductList();
			
		for(int i=0; i< products.size(); i++) {
			try {
				PreparedStatement psmt = conn.prepareStatement(CREATE_SHIPPING_PRODUCTS);
					
				psmt.setString(1, model.getMemberId());
				psmt.setString(2, String.valueOf(model.getOrderId()));
				psmt.setTimestamp(3, TimeStamp.getTimestampKorea());
//				psmt.setString(4, products.get(i).getShopUrl());
//				psmt.setString(5, products.get(i).getTrackingTitle());
//				psmt.setString(6, products.get(i).getTrackingNumber());
				psmt.setString(7, products.get(i).getCategoryTitle());
				psmt.setString(8, products.get(i).getItemTitle());
				psmt.setString(9, products.get(i).getBrandName());
				psmt.setString(10, products.get(i).getItemName());
				psmt.setInt(11, products.get(i).getAmount());
				psmt.setDouble(12, products.get(i).getPrice());
				psmt.setDouble(13, products.get(i).getTotalPrice());
					
				psmt.executeUpdate();
			} catch (SQLException e) {
					
			} finally {
				 
			}
		}
		try {
            if (psmt != null) {
            	psmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
	}
	
	private static final String CREATE_SHIPPING_SERVICE = 
			"insert into recipient(memberId, orderId, name_kor, name_eng,"
			+ "transit_nr, phone_prefic, phone_interfix, phone_suffix, zip_code,"
			+ "address, detail_address, userComment ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String CREATE_SHIPPING_PRODUCTS = "insert into product(memberId, orderId, order_stamp, "
			+ "pd_shopurl, pd_trackingtitle, pd_trackingnumber,"
			+ "pd_categorytitle, pd_itemtitle, pd_brandname, pd_itemname, "
			+ "pd_amount, pd_price, pd_totalprice) values(?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
	
	private static final String CREATE_SHIPPING_ORDER_STATE = "insert into orderstate(memberId, orderId, ship_price, "
			+ "pay_state, ship_state) values(?,?,?,?,?)"; 
	
	
	private void close() {
        try {
            if (psmt != null) {
            	psmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
    }
}

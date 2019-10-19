package shippingService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import databaseUtil.ConnectionDB;
import util.TimeStamp;

public class ShippingServiceDAO {
	
	public ShippingServiceDAO() {}
	
	public void createShippingServiceDB(ShippingServiceModel model) throws SQLException {
		ConnectionDB.connectSQL();		
		/** 
		 *  memberId varchar(50) not null,
            orderId varchar(50) not null,
            name_kor varchar(50) not null,
            name_eng varchar(50) not null,
            transit_nr varchar(50) not null,
            phone_prefic integer,
            phone_interfix integer,
            phone_suffix integer,
            zip_code integer,
            address text,
            detail_adress text,
            userComment text 
           */	
		try (Connection conn = ConnectionDB.getConnectInstance();
		        PreparedStatement psmt = conn.prepareStatement(CREATE_SHIPPING_SERVICE);) {
			psmt.setString(1, model.getMemberId());
			psmt.setString(2, String.valueOf(model.getOrderId()));
			psmt.setString(3, model.getReceiverNameByKorea());
			psmt.setString(4, model.getReceiverNameByEnglish());
			psmt.setString(5, model.getTransitNumber());
			psmt.setString(6, model.getCallNumberFront());
			psmt.setString(7, model.getCallNumberMiddle());
			psmt.setString(8, model.getCallNumberRear());
			psmt.setString(9, model.getPostCode());
			psmt.setString(10, model.getDeliveryAddress());
			psmt.setString(11, model.getDetailAddress());
			psmt.setString(12, model.getDeliveryMessage());
	
			psmt.executeUpdate();
		} catch (SQLException e) {
		    System.out.println(e);
		} 
		
		ResultSet resultSet = null;
		ConnectionDB.connectSQL();
		int paymentid = 0;
		try (Connection conn = ConnectionDB.getConnectInstance();
		        PreparedStatement psmt = conn.prepareStatement(CREATE_SHIPPING_PAYMENT);) {
            psmt.setString(1, model.getMemberId());
            psmt.setString(2, String.valueOf(model.getOrderId()));
            psmt.setInt(3, model.getPaymentState());
            resultSet  = psmt.executeQuery();
            paymentid = getPaymentId(resultSet); 
		} catch (SQLException e) {
		    System.out.println(e);
        } 
		
		ConnectionDB.connectSQL();
		try (Connection conn = ConnectionDB.getConnectInstance();
		        PreparedStatement psmt = conn.prepareStatement(CREATE_SHIPPING_ORDER_STATE);){
		    /** 
	         *  memberid character varying(50) 
                orderid character varying(50) 
                ship_price numeric,
                ship_state integer,
                
                trackingnr_kor character varying(50) 
                trackingnr_world character varying(50) 
                box_actual_weight numeric,
                box_volume_weight numeric,
                ship_price_discount numeric,
                fk_payment integer,
                
                tracking_company_kor character varying(50) 
                tracking_company_world character varying(50)
                shop_url character varying(50)
	           */
			
			psmt.setString(1, model.getMemberId());
			psmt.setString(2, String.valueOf(model.getOrderId()));
			psmt.setDouble(3, model.getShippingPrice());
			psmt.setInt(4, model.getShipState());
			psmt.setString(5, model.getTrackingCompany());
			psmt.setString(6, model.getTrackingNumber());
			psmt.setInt(7, paymentid);
			psmt.setString(8, model.getShopUrl());
			psmt.executeUpdate();
		} catch (SQLException e) {
		    System.out.println(e);
		} 

		/** 
         *memberid character varying(50) NOT NULL,
          orderid character varying(50) NOT NULL,
          order_stamp timestamp without time zone NOT NULL,
          pd_categorytitle character varying(50),
          pd_itemtitle character varying(50),
          pd_brandname character varying(50),
          pd_itemname character varying(50),
          pd_amount integer NOT NULL,
          pd_price numeric NOT NULL,
          pd_totalprice numeric NOT NULL,
           */   

		ArrayList<ShippingProduct> products = model.getShippingProductList();
		ConnectionDB.connectSQL();
		for(int i=0; i< products.size(); i++) {
			try (Connection conn = ConnectionDB.getConnectInstance();
			        PreparedStatement psmt = conn.prepareStatement(CREATE_SHIPPING_PRODUCTS);) {
					
				psmt.setString(1, model.getMemberId());
				psmt.setString(2, String.valueOf(model.getOrderId()));
				psmt.setTimestamp(3, TimeStamp.getTimestampKorea());
				psmt.setString(4, products.get(i).getCategoryTitle());
				psmt.setString(5, products.get(i).getItemTitle());
				psmt.setString(6, products.get(i).getBrandName());
				psmt.setString(7, products.get(i).getItemName());
				psmt.setInt(8, products.get(i).getProductAmount());
				psmt.setDouble(9, products.get(i).getProductPrice());
				psmt.setDouble(10, products.get(i).getProductTotalPrice());
					
				psmt.executeUpdate();
			} catch (SQLException ex) {
			    System.out.println(ex);	
			} 
		}
	}
	
	private int getPaymentId(ResultSet rs) throws SQLException {
	    int paymentid=0;
	    while (rs.next()) {
	        paymentid = rs.getInt("paymentid");
	    }
	    return paymentid;
	}
	
 
	private static final String CREATE_SHIPPING_SERVICE = 
			"insert into recipient(memberId, orderId, name_kor, name_eng,"
			+ "transit_nr, phone_prefic, phone_interfix, phone_suffix, zip_code,"
			+ "address, detail_address, userComment ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String CREATE_SHIPPING_PRODUCTS = "insert into product(memberId, orderId, order_stamp, "
			+ "pd_categorytitle, pd_itemtitle, pd_brandname, pd_itemname, "
			+ "pd_amount, pd_price, pd_totalprice) values(?,?,?,?,?,?,?,?,?,?)"; 
	
	private static final String CREATE_SHIPPING_ORDER_STATE = "insert into orderstate(memberId, orderId, ship_price, ship_state, "
	        + "tracking_company_world, trackingnr_world, fk_payment, shop_url) values(?,?,?,?,?,?,?,?)";
	
	private static final String CREATE_SHIPPING_PAYMENT = 
	        "insert into payment(memberId, orderId, payment_state) values(?,?,?) RETURNING payment.paymentid";
    
}
package mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import databaseUtil.ConnectionDB;
import mypage.information.ProductsCommonInformation;
import mypage.information.ProductsInformation;
import mypage.information.ProductsInformation.Product;
import mypage.information.RecipientData;
import payment.PaymentState;
import shippingService.ShippingServiceState;

public class MypageDetailsImpl implements MypageDetailsDAO {
	
	private final int PAYMENT_REQEUST_STATE = 2; //결제요청 - 무통장입금전
	
	/*bulider pattern*/
	@Override
	public OrderingPersonInformation getOrderingpersonInfo(String fullname) {
		OrderingPersonInformation orderingPersonInfo = new OrderingPersonInformation();
		orderingPersonInfo.setFullname(fullname);
		orderingPersonInfo.setShipServiceCenter("독일");
		return orderingPersonInfo;
	}

	//factory pattern
	//https://alvinalexander.com/java/java-factory-pattern-example
	@Override
	public RecipientData getRecipientInfo(String username, String number) {
		ResultSet resultSet = null;
		ConnectionDB.connectSQL();
		String query = "SELECT * FROM RECIPIENT WHERE memberid=? AND orderid=?";
		RecipientData recipient = new RecipientData();
		try (Connection conn = ConnectionDB.getConnectInstance();
				PreparedStatement psmt = conn.prepareStatement(query);){
			psmt.setString(1, username);
			psmt.setString(2, number);
			resultSet = psmt.executeQuery();
			recipient = writeRecipientInformation(resultSet, recipient);
		} catch (SQLException e) {
			//Logger
		}		
		return recipient;
	}
	
//	payment
//	1 결제대기 - 결제전
//	2 결제요청 - 무통장입금전
//	3 결제완료
	
//	shipState
//	입고대기 (1),
//	입고완료 (2),
//	결제요청 (3),
//	결제완료 (4),
//	해외배송중 (5),
//	통관진행 (6),
//	국내배송 (7),
//	배송완료 (8)

	@Override
	public void willPayDeliveryFee(String username, String orderNumber, String ownerName) {
		ConnectionDB.connectSQL();
		//String query = "UPDATE PAYMENT SET payment_ownername = ?, payment_state = ? WHERE memberid=? AND orderid=?";
		String query = "WITH t AS ( UPDATE PAYMENT SET payment_ownername = ?, "
				+ "payment_state = ?  "
				+ "WHERE memberid = ? AND orderid = ?) "
				+ "UPDATE orderstate SET ship_state = ?  "
				+ "WHERE memberid = ? AND orderid = ?";
		try (Connection conn = ConnectionDB.getConnectInstance();
				PreparedStatement psmt = conn.prepareStatement(query);){
			psmt.setString(1, ownerName);
			psmt.setInt(2, PaymentState.REQUEST.getCode());
			psmt.setString(3, username);
			psmt.setString(4, orderNumber);
			
			psmt.setInt(5, ShippingServiceState.PAYMENT_READY.getCode());
			psmt.setString(6, username);
			psmt.setString(7, orderNumber);
			
			psmt.executeQuery();
		} catch (SQLException e) {
			//Logger
		}	
	}
	
	public RecipientData writeRecipientInformation(ResultSet rs, RecipientData recipient) throws SQLException {
		while (rs.next()) {
			recipient.setNameKor(rs.getString("name_kor"));
			recipient.setNameEng(rs.getString("name_eng"));
			recipient.setTransitNr(rs.getString("transit_nr"));
			recipient.setPhonePrefic(rs.getString("phone_prefic"));
			recipient.setPhoneInterfix(rs.getString("phone_interfix"));
			recipient.setPhoneSuffix(rs.getString("phone_suffix"));
			recipient.buildPhoneNr();
			recipient.setZipCode(rs.getString("zip_code"));
			recipient.setAddress(rs.getString("address"));
			recipient.setAddressDetails(rs.getString("detail_address"));
			//recipient.buildFullAdress();
			recipient.setUsercomment(rs.getString("usercomment"));
		}
		return recipient;
	}
	
	@Override
	public List<Product> getProductsInfo(String username, String number) {
		ResultSet resultSet = null;
		ConnectionDB.connectSQL();
		String query = "SELECT memberid, orderid, pd_shopurl, pd_trackingtitle, pd_trackingnumber, pd_categorytitle"
				+ ", pd_itemtitle, pd_itemname,pd_brandname, pd_amount, pd_price, pd_totalprice  FROM PRODUCT WHERE memberid=? AND orderid=?";
		ProductsInformation productsInfo = new ProductsInformation();
		try (Connection conn = ConnectionDB.getConnectInstance();
				PreparedStatement psmt = conn.prepareStatement(query);){
			psmt.setString(1, username);
			psmt.setString(2, number);
			resultSet = psmt.executeQuery();
			productsInfo = writeProductsInformation(resultSet, productsInfo);
		} catch (SQLException e) {
			//Logger
		}		
		return productsInfo.getProductsList();
	}
	
	public ProductsInformation writeProductsInformation(ResultSet rs, ProductsInformation products) throws SQLException {
		while (rs.next()) {
			/*refactoring divide table shopurl, tracking..*/
			Product product = products.createProduct();
			product.setCategorytitle(rs.getString("pd_categorytitle"));
			product.setItemtitle(rs.getString("pd_itemtitle"));
			product.setItemName(rs.getString("pd_itemname"));
			product.setBrandname(rs.getString("pd_brandname"));
			product.setAmount(rs.getInt("pd_amount"));
			product.setPrice(rs.getDouble("pd_price"));
			product.setTotalPrice(rs.getDouble("pd_totalprice"));
			products.getProductsList().add(product);
		}
		return products;
	}
	
	@Override
	public ProductsCommonInformation getProductsCommonInfo(String username, String orderNumber) {
		ResultSet resultSet = null;
		ConnectionDB.connectSQL();
		String query = "SELECT DISTINCT prod.pd_shopurl, prod.pd_trackingtitle, prod.pd_trackingnumber, "
				+ "oState.ship_state, oState.ship_price, oState.box_actual_weight, oState.box_volume_weight, oState.ship_price_discount, paymt.payment_ownername, paymt.payment_state  "
				+ "FROM ((PRODUCT prod INNER JOIN ORDERSTATE oState "
				+ "ON prod.orderid = oState.orderid and prod.memberid=? and prod.orderid=?) "
				+ "INNER JOIN PAYMENT paymt ON oState.fk_payment = paymt.paymentid and oState.orderid = ?)";
		ProductsCommonInformation commonInfo = new ProductsCommonInformation();
		try (Connection conn = ConnectionDB.getConnectInstance();
				PreparedStatement psmt = conn.prepareStatement(query);){
			psmt.setString(1, username);
			psmt.setString(2, orderNumber);
			psmt.setString(3, orderNumber);
			resultSet = psmt.executeQuery();
			commonInfo = writeProductsCommonInformation(resultSet, commonInfo);
		} catch (SQLException e) {
			//Logger
		}
		
		ResultSet resultSetForPrice = null;
		ConnectionDB.connectSQL();
		String totalPriceQuery = "SELECT pd_totalprice FROM PRODUCT WHERE memberid=? AND orderid=?";
		try (Connection conn = ConnectionDB.getConnectInstance();
				PreparedStatement psmt = conn.prepareStatement(totalPriceQuery);){
			psmt.setString(1, username);
			psmt.setString(2, orderNumber);
			resultSetForPrice = psmt.executeQuery();
			commonInfo = writeTotalPriceProductsCommonInformation(resultSetForPrice, commonInfo);
		} catch (SQLException e) {
			//Logger
		}
		return commonInfo;
	}
	
	public ProductsCommonInformation writeTotalPriceProductsCommonInformation(ResultSet rs, ProductsCommonInformation commonInfo) throws SQLException {
		double price=0.0;
		while (rs.next()) {
			price = price + rs.getDouble("pd_totalprice");
		}
		commonInfo.setTotalPrice(price);
		return commonInfo;
	}
	
	public ProductsCommonInformation writeProductsCommonInformation(ResultSet rs, ProductsCommonInformation commonInfo) throws SQLException {
		while (rs.next()) {
			/*refactoring divide table shopurl, tracking..and perform ones*/
			commonInfo.setShopUrl(rs.getString("pd_shopurl"));
			commonInfo.setTrackingCompany(rs.getString("pd_trackingtitle"));
			commonInfo.setTrackingNr(rs.getString("pd_trackingnumber"));
			commonInfo.setShipState(rs.getInt("ship_state"));
			commonInfo.setShipPrice(rs.getInt("ship_price"));
			commonInfo.setActualWeight(rs.getDouble("box_actual_weight"));
			commonInfo.setVolumeWeight(rs.getDouble("box_volume_weight"));
			commonInfo.setPaymentOwnerName(rs.getString("payment_ownername"));
			commonInfo.setPaymentState(rs.getInt("payment_state"));
		}
		return commonInfo;
	}
	
	public ResponseEntity<?> updateRecipientData(String memberId, HashMap<String, Object>[] data) {
	    String orderNumber = data[0].get("orderNumber").toString();
        String nameKor = data[1].get("nameKor").toString();
        String nameEng = data[2].get("nameEng").toString();
        String transitNr = data[3].get("transitNr").toString();
        String phonePrefic = data[4].get("phonePrefic").toString();
        String phoneInterfix = data[5].get("phoneInterfix").toString();
        String phoneSuffix = data[6].get("phoneSuffix").toString();
        String zipCode = data[7].get("zipCode").toString();
        String address = data[8].get("address").toString();
        String addressDetails = data[9].get("addressDetails").toString();
        String usercomment = data[10].get("usercomment").toString();
        
        ConnectionDB.connectSQL();
        final String UPDATE_RECIPIENT_DATA = 
                "UPDATE recipient SET name_kor = ?, name_eng = ?, transit_nr = ?, phone_prefic = ?, phone_interfix = ?, phone_suffix = ?, zip_code = ?, address= ?, detail_address = ?, usercomment = ? "
                + "where memberid = ? and orderid = ?";
        
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(UPDATE_RECIPIENT_DATA);){
            psmt.setString(1, nameKor);
            psmt.setString(2, nameEng);
            psmt.setString(3, transitNr);
            psmt.setString(4, phonePrefic);
            psmt.setString(5, phoneInterfix);
            psmt.setString(6, phoneSuffix);
            psmt.setString(7, zipCode);
            psmt.setString(8, address);
            psmt.setString(9, addressDetails);
            psmt.setString(10, usercomment);
            psmt.setString(11, memberId);
            psmt.setString(12, orderNumber);
            
            psmt.executeUpdate();
        } catch (SQLException e) {
            //Logger
        }
        
	    HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
	}
}
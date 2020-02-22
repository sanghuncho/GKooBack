package mypage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkoo.data.BuyingServiceDetailData;
import com.gkoo.data.RecipientData;
import com.gkoo.repository.ShippingServiceRepository;
import databaseUtil.ConnectionDB;
import mypage.information.ProductsCommonInformation;
import mypage.information.ProductsInformation;
import mypage.information.ProductsInformation.Product;
import payment.PaymentData;
import payment.PaymentState;
import shippingService.DeliveryDataObject;
import shippingService.ShippingProduct;
import shippingService.ShippingServiceModel;
import shippingService.ShippingServiceState;

@Service
public class MypageDetailsImpl implements MypageDetailsDAO {
    private static final Logger LOGGER = LogManager.getLogger();
	private final int PAYMENT_REQEUST_STATE = 2; //결제요청 - 무통장입금전
	private ShippingServiceRepository shippingServiceRepository;
	
	@Autowired
	public MypageDetailsImpl(ShippingServiceRepository shippingServiceRepository) {
	    this.shippingServiceRepository = shippingServiceRepository;
	}
	
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
	public RecipientData getRecipientInfo(String userid, String orderid) {
		ResultSet resultSet = null;
		ConnectionDB.connectSQL();
		String query = "SELECT * FROM RECIPIENT WHERE userid=? AND orderid=?";
		RecipientData recipient = new RecipientData();
		try (Connection conn = ConnectionDB.getConnectInstance();
				PreparedStatement psmt = conn.prepareStatement(query);){
			psmt.setString(1, userid);
			psmt.setString(2, orderid);
			resultSet = psmt.executeQuery();
			recipient = writeRecipientInformation(resultSet, recipient);
		} catch (SQLException e) {
			//Logger
		}		
		return recipient;
	}
	
	@Override
    public RecipientData getRecipientBuyingService(String userid, String orderid) {
        ResultSet resultSet = null;
        ConnectionDB.connectSQL();
        //SELECT * FROM BUYING_SERVICE bs, BUYING_SERVICE_RECIPIENT bsr WHERE bs.orderid='20200214055847' and  bs.object_id=bsr.fk_buying_service
        String query = "SELECT * FROM BUYING_SERVICE bs, BUYING_SERVICE_RECIPIENT bsr WHERE bs.orderid = ? and bs.object_id = bsr.fk_buying_service";
        RecipientData recipient = new RecipientData();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(query);){
            //psmt.setString(1, userid);
            psmt.setString(1, orderid);
            resultSet = psmt.executeQuery();
            recipient = writeRecipientInformation(resultSet, recipient);
        } catch (SQLException ex) {
            String error = "Error fetching the recipient for buying service";
            LOGGER.error(error, ex);
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
	public void willPayDeliveryFee(String userid, String orderid, String ownerName) {
		ConnectionDB.connectSQL();
		//String query = "UPDATE PAYMENT SET payment_ownername = ?, payment_state = ? WHERE memberid=? AND orderid=?";
		String query = "WITH t AS ( UPDATE PAYMENT SET payment_ownername = ?, "
				+ "payment_state = ?  "
				+ "WHERE userid = ? AND orderid = ?) "
				+ "UPDATE orderstate SET ship_state = ?  "
				+ "WHERE userid = ? AND orderid = ?";
		try (Connection conn = ConnectionDB.getConnectInstance();
				PreparedStatement psmt = conn.prepareStatement(query);){
			psmt.setString(1, ownerName);
			psmt.setInt(2, PaymentState.REQUEST.getCode());
			psmt.setString(3, userid);
			psmt.setString(4, orderid);
			
			psmt.setInt(5, ShippingServiceState.PAYMENT_READY.getCode());
			psmt.setString(6, userid);
			psmt.setString(7, orderid);
			
			psmt.executeQuery();
		} catch (SQLException e) {
			//Logger
		}	
	}
	
	private RecipientData writeRecipientInformation(ResultSet rs, RecipientData recipient) throws SQLException {
		while (rs.next()) {
			recipient.setNameKor(rs.getString("name_kor"));
			recipient.setNameEng(rs.getString("name_eng"));
			recipient.setTransitNr(rs.getString("transit_nr"));
			recipient.setPhonenumberFirst(rs.getString("phonenumber_first"));
			recipient.setPhonenumberSecond(rs.getString("phonenumber_second"));
			recipient.setZipCode(rs.getString("zip_code"));
			recipient.setAddress(rs.getString("address"));
			recipient.setUsercomment(rs.getString("usercomment"));
		}
		return recipient;
	}
	
	@Override
	public List<Product> getProductsInfo(String username, String number) {
		ResultSet resultSet = null;
		ConnectionDB.connectSQL();
		String query = "SELECT memberid, orderid, pd_categorytitle"
				+ ", pd_itemtitle, pd_itemname, pd_brandname, pd_amount, pd_price, pd_totalprice  FROM PRODUCT WHERE memberid=? AND orderid=?";
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
			product.setCategoryTitle(rs.getString("pd_categorytitle"));
			product.setItemTitle(rs.getString("pd_itemtitle"));
			product.setItemName(rs.getString("pd_itemname"));
			product.setBrandName(rs.getString("pd_brandname"));
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
		String query = "SELECT oState.shop_url, oState.tracking_company_world, oState.trackingnr_world, oState.ship_state, oState.ship_price, oState.box_actual_weight, oState.box_volume_weight, oState.ship_price_discount, paymt.paymentid, paymt.payment_date, paymt.payment_deposit ,paymt.payment_ownername, paymt.payment_state FROM ORDERSTATE oState JOIN PAYMENT paymt ON oState.userid = ? and oState.orderid = ? and oState.id = paymt.fk_orderstate";
		//String query = "SELECT * from ORDERSTATE where memberid=? and orderid = ? INNER JOIN PAYMENT paymt ON oState.fk_payment = paymt.paymentid and oState.orderid = ?";
		//ToDo : Jackson
		//select oState.shop_url, oState.ship_state, paymt.payment_state FROM ORDERSTATE oState 
		//JOIN PAYMENT paymt on oState.fk_payment = paymt.paymentid and oState.orderid='20191019145523';                      
		ProductsCommonInformation commonInfo = new ProductsCommonInformation();
		try (Connection conn = ConnectionDB.getConnectInstance();
				PreparedStatement psmt = conn.prepareStatement(query);){
			psmt.setString(1, username);
			psmt.setString(2, orderNumber);
			resultSet = psmt.executeQuery();
			commonInfo = writeProductsCommonInformation(resultSet, commonInfo);
		} catch (SQLException e) {
		    System.out.println(e);  
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
		    commonInfo.setShipPrice(rs.getInt("ship_price"));
		    commonInfo.setPaymentState(rs.getInt("payment_state"));
		    commonInfo.setShipState(rs.getInt("ship_state"));
		    commonInfo.setShopUrl(rs.getString("shop_url"));
		    commonInfo.setTrackingCompany(rs.getString("tracking_company_world"));
			commonInfo.setTrackingNr(rs.getString("trackingnr_world"));
			commonInfo.setActualWeight(rs.getDouble("box_actual_weight"));
			commonInfo.setVolumeWeight(rs.getDouble("box_volume_weight"));
			commonInfo.setPaymentOwnerName(rs.getString("payment_ownername"));
			commonInfo.setPaymentid(rs.getInt("paymentid"));
			commonInfo.setPaymentDate(rs.getDate("payment_date"));
			commonInfo.setPaymentDeposit(rs.getDouble("payment_deposit"));
		}
		return commonInfo;
	}
	
	public ResponseEntity<?> updateRecipientData(String userid, HashMap<String, Object>[] data) {
	    String orderid = data[0].get("orderid").toString();
        String nameKor = data[1].get("nameKor").toString();
        String nameEng = data[2].get("nameEng").toString();
        String transitNr = data[3].get("transitNr").toString();
        String phonenumberFirst = data[4].get("phonenumberFirst").toString();
        String phonenumberSecond = data[5].get("phonenumberSecond").toString();
        String zipCode = data[6].get("zipCode").toString();
        String address = data[7].get("address").toString();
        String usercomment = data[8].get("usercomment").toString();
        
        ConnectionDB.connectSQL();
        final String UPDATE_RECIPIENT_DATA = 
                "UPDATE recipient SET name_kor = ?, name_eng = ?, transit_nr = ?, phonenumber_first = ?, phonenumber_second = ?, zip_code = ?, address= ?, usercomment = ? "
                + "where userid = ? and orderid = ?";
        
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(UPDATE_RECIPIENT_DATA);){
            psmt.setString(1, nameKor);
            psmt.setString(2, nameEng);
            psmt.setString(3, transitNr);
            psmt.setString(4, phonenumberFirst);
            psmt.setString(5, phonenumberSecond);
            psmt.setString(6, zipCode);
            psmt.setString(7, address);
            psmt.setString(8, usercomment);
            psmt.setString(9, userid);
            psmt.setString(10, orderid);
            
            psmt.executeUpdate();
        } catch (SQLException e) {
            //Logger
        }
        
	    HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<?> updateDataEditorProductsList(String userid, HashMap<String, Object>[] data) throws JsonParseException, JsonMappingException, IOException, SQLException {
	    String orderid = data[0].get("orderid").toString();
	    
	    ObjectMapper mapper = new ObjectMapper();
        DeliveryDataObject deliveryDataObj = mapper.readValue(data[1].get("deliveryDataObject").toString(), DeliveryDataObject.class);
        ShippingProduct[] shippingProducts = mapper.readValue(data[2].get("shippingProductList").toString(), ShippingProduct[].class);
        
        ShippingServiceModel shippingModel = new ShippingServiceModel();
        shippingModel.setOrderId(orderid);
        shippingModel.setUserid(userid);
        shippingModel.setDeliveryData(deliveryDataObj);
        shippingModel.setShippingProductsList(shippingProducts);
        
        return shippingServiceRepository.updateDataShippingService(shippingModel);
	}
	
	public ResponseEntity<?> deleteShipingServiceData(String userid, HashMap<String, Object>[] data) throws JsonParseException, JsonMappingException, IOException, SQLException {
        String orderNumber = data[0].get("orderNumber").toString();
        return shippingServiceRepository.deleteShipingServiceData(userid, orderNumber);
    }

    @Override
    public MypageDetailData getMypageDetailData(String userid, String orderid) {
        RecipientData recipientData = getRecipientInfo(userid, orderid);
        ProductsCommonInformation productsCommonInformation = getProductsCommonInfo(userid, orderid);  
        return new MypageDetailData(recipientData, productsCommonInformation);
    }

    @Override
    public BuyingServiceDetailData getMypageBuyingServiceDetailData(String userid, String orderid) {
        RecipientData recipientData = getRecipientBuyingService(userid, orderid);
        PaymentData productPayment =  getPaymentProductBuyingServiceByOrderid(orderid);
        PaymentData deliveryPayment = getPaymentDeliveryBuyingServiceByOrderid(orderid);
        List<Product> productsList = getProductDataBuyingService(userid, orderid);
        return new BuyingServiceDetailData(recipientData, productPayment, deliveryPayment, productsList);
    }

    @Override
    public PaymentData getPaymentProductBuyingServiceByOrderid(String orderid) {
        ConnectionDB.connectSQL();
        final String GET_PAYMENT_PRODUCT_DATA = "SELECT bsp.object_id, bsp.buying_service_payment_state, bsp.buying_deposit_ownername, bsp.payment_art,"
                + "bs.orderid, bs.buying_price, bs.buying_service_state FROM BUYING_SERVICE_PAYMENT bsp, BUYING_SERVICE bs WHERE bs.orderid=? and bs.object_id=bsp.fk_buying_service";
        ResultSet resultSet = null;
        PaymentData paymentData = null;
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(GET_PAYMENT_PRODUCT_DATA);){
            psmt.setString(1, orderid);
            resultSet = psmt.executeQuery();
            paymentData = writePaymentProductBuyingService(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching paymentData";
            LOGGER.error(error, e);
        }
        return paymentData;
    }
    
    private static PaymentData writePaymentProductBuyingService(ResultSet rs){
        PaymentData payment = null;
        try {
            while (rs.next()) {
                payment = new PaymentData();
                try {
                    payment.setPaymentid(rs.getInt("object_id"));
                    payment.setOrderid(rs.getString("orderid"));
                    payment.setPaymentState(rs.getInt("buying_service_payment_state"));
                    payment.setBuyingServiceState(rs.getInt("buying_service_state"));
                    payment.setBuyingPrice(rs.getDouble("buying_price"));
                    payment.setPaymentOwnername(rs.getString("buying_deposit_ownername"));
                    payment.setPaymentArt(rs.getInt("payment_art"));
                } catch (SQLException e) {
                    String error = "Error fetching paymentData";
                    LOGGER.error(error, e);
                }
            }
        } catch (SQLException e) {
            String error = "Error fetching payment product Data by orderid";
            LOGGER.error(error, e);
        }
        return payment;
    }
    
    @Override
    public PaymentData getPaymentDeliveryBuyingServiceByOrderid(String orderid) {
        ConnectionDB.connectSQL();
        final String GET_PAYMENTDATA = "SELECT bsp.object_id, bsp.buying_service_payment_state, bsp.shipping_deposit_ownername, "
                + "bsp.payment_art_shipping_price, bs.orderid, bs.ship_price, "
                + "bs.box_actual_weight, bs.box_volume_weight "
                + "FROM BUYING_SERVICE_PAYMENT bsp, BUYING_SERVICE bs WHERE bs.orderid = ? "
                + "and bs.object_id=bsp.fk_buying_service and (bsp.buying_service_payment_state = 3 or bsp.buying_service_payment_state = 4)";
        
        ResultSet resultSet = null;
        PaymentData paymentData = null;
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(GET_PAYMENTDATA);){
            psmt.setString(1, orderid);
            resultSet = psmt.executeQuery();
            paymentData = writePaymentDeliveryBuyingService(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching paymentData";
            LOGGER.error(error, e);
        }
        return paymentData;
    }
    
    private static PaymentData writePaymentDeliveryBuyingService(ResultSet rs){
        PaymentData payment = null;
        try {
            while (rs.next()) {
                payment = new PaymentData();
                try {
                    payment.setPaymentid(rs.getInt("object_id"));
                    payment.setOrderid(rs.getString("orderid"));
                    payment.setPaymentState(rs.getInt("buying_service_payment_state"));
                    payment.setShipPrice(rs.getDouble("ship_price"));
                    payment.setBoxActualWeight(rs.getDouble("box_actual_weight"));
                    payment.setBoxVolumeWeight(rs.getDouble("box_volume_weight"));
                    payment.setPaymentOwnername(rs.getString("shipping_deposit_ownername"));
                    payment.setPaymentArt(rs.getInt("payment_art_shipping_price"));
                } catch (SQLException e) {
                    String error = "Error fetching paymentDeliveryData";
                    LOGGER.error(error, e);
                }
            }
        } catch (SQLException e) {
            String error = "Error fetching paymentDeliveryData";
            LOGGER.error(error, e);
        }
        return payment;
    }

    @Override
    public List<Product> getProductDataBuyingService(String userid, String orderid) {
        ResultSet resultSet = null;
        ConnectionDB.connectSQL();
        String query = "SELECT bsp.pd_categorytitle"
                + ", bsp.pd_itemtitle, bsp.pd_itemname, bsp.pd_brandname, bsp.pd_amount, bsp.pd_price, bsp.pd_totalprice "
                + "FROM BUYING_SERVICE bs, BUYING_SERVICE_PRODUCT bsp WHERE (bs.userid = ? AND bs.orderid = ?) AND (bs.object_id = bsp.fk_buying_service)";
        ProductsInformation productsInfo = new ProductsInformation();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(query);){
            psmt.setString(1, userid);
            psmt.setString(2, orderid);
            resultSet = psmt.executeQuery();
            productsInfo = writeProductsInformation(resultSet, productsInfo);
        } catch (SQLException e) {
            String error = "Error fetching products data for buyingService";
            LOGGER.error(error, e);
        }       
        return productsInfo.getProductsList();
    }
}
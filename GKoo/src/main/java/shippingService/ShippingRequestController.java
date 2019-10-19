package shippingService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import payment.PaymentState;
import util.MemberProfile;
import util.OrderID;
import util.TimeStamp;

@RestController
public class ShippingRequestController {
	
	private final double INITIAL_PRICE = 0;
	/**
     *Todo: using spring annotation 
     */		
	public ShippingRequestController() {}
	
	@CrossOrigin(origins = "http://localhost:3000/requestshipping")
	@RequestMapping(value = "/createshippingservice", method = RequestMethod.POST)
	public ResponseEntity<?> requestShippingservice(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException, JsonParseException, JsonMappingException, IOException {
	
		System.out.println("배송대행 서비스 신청시작");
		ShippingServiceModel shippingModel = new ShippingServiceModel();
		ShippingServiceDAO shipServiceDao = new ShippingServiceDAO();
		
		String memberId = MemberProfile.getMemberID(request);
        String timeStamp = TimeStamp.getCurrentTimeStampKorea();
        String orderId = OrderID.generateOrderID();
        System.out.println("배송대행 서비스주문번호: " + orderId);
		
        shippingModel.setMemberId(memberId);
        shippingModel.setTimeStamp(timeStamp);
        shippingModel.setOrderId(orderId);
        shippingModel.setEasyship(data[0].get("easyship").toString());
        
        ObjectMapper mapper = new ObjectMapper();
        DeliveryDataObject deliveryDataObj = mapper.readValue(data[1].get("deliveryDataObject").toString(), DeliveryDataObject.class);
        ShippingProduct[] shippingProducts = mapper.readValue(data[2].get("shippingProductList").toString(), ShippingProduct[].class);
        
        shippingModel.setDeliveryData(deliveryDataObj);
        shippingModel.setShippingProductsList(shippingProducts);

		/**
	     *ToDO: move to shippingServiceModel
	     *ToDO: Build pattern
	     *ToDO: Jackson
	     */
		shippingModel.setReceiverNameByKorea(data[3].get("receiverNameByKorea").toString());
		shippingModel.setOwnerContent(data[4].get("setOwnerContent").toString());
		shippingModel.setReceiverNameByEnglish(data[5].get("receiverNameByEnglish").toString());
		
		shippingModel.setPrivateTransit(data[6].get("privateTransit").toString());
		shippingModel.setTransitNumber(data[7].get("transitNumber").toString());
		shippingModel.setAgreeWithCollection(data[8].get("agreeWithCollection").toString());
		
		shippingModel.setCallNumberFront(data[9].get("callNumberFront").toString());
		shippingModel.setCallNumberMiddle(data[10].get("callNumberMiddle").toString());
		shippingModel.setCallNumberRear(data[11].get("callNumberRear").toString());
		
		shippingModel.setPostCode(data[12].get("postCode").toString());
		shippingModel.setDeliveryAddress(data[13].get("deliveryAddress").toString());
		shippingModel.setDetailAddress(data[14].get("detailAddress").toString());
		shippingModel.setDeliveryMessage(data[15].get("deliveryMessage").toString());
		
		/** 국제배송비 */
		shippingModel.setShippingPrice(INITIAL_PRICE);
		/** 국제배송 상태 */
		shippingModel.setShipState(ShippingServiceState.RECEIVE_BOX_READY);
		shippingModel.setPaymentState(PaymentState.BEFORE);
		
		shipServiceDao.createShippingServiceDB(shippingModel);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	
	/**
     *ToDo:json structure should be modified
     */
	private ArrayList<String> transformArrayList(String key, String val){
		ArrayList<String> arrayList = new ArrayList<>(); 
		
		String predef = "{" + key + ":";
		String content = val;
		String postdef = "}";
		String convert = predef.concat(content);
		String result = convert.concat(postdef);
		
		JSONObject obj = new JSONObject(result);		
		JSONArray arr = obj.getJSONArray(key);

		for (int i = 0; i < arr.length(); i++)
		{
		    String in = arr.getString(i);
		    arrayList.add(in);
		}
		return arrayList;
	}
}

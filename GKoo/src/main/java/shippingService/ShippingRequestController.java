package shippingService;

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

import util.MemberProfile;
import util.OrderID;
import util.TimeStamp;

@RestController
public class ShippingRequestController {
	
	/**
     *Todo: using spring annotation 
     */
			
	public ShippingRequestController() {}
	
	@CrossOrigin(origins = "http://localhost:3000/requestshipping")
	@RequestMapping(value = "/createshippingservice", method = RequestMethod.POST)
	public ResponseEntity<?> requestShippingservice(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException {
	
		ShippingServiceModel shippingModel = new ShippingServiceModel();
		ShippingServiceDAO shipServiceDao = new ShippingServiceDAO();
		
		String memberId = MemberProfile.getMemberID(request);
        String timeStamp = TimeStamp.getCurrentTimeStampKorea();
        String orderId = OrderID.generateOrderID();
        shippingModel.setMemberId(memberId);
        shippingModel.setTimeStamp(timeStamp);
        shippingModel.setOrderId(orderId);
        
		/**
	     *Todo: amount and price
	     */
        shippingModel.addProduct(data);

        /**
	     *From here additional product, other list should be implemented.
	     *ToDO: set-method and add-method integrated
	     */
		shippingModel.setShopUrlList(transformArrayList("shopUrlList", data[22].get("shopUrlList").toString()));
		shippingModel.addMoreProducts();
		
		/**
	     *ToDO: move to shippingServiceModel
	     */
		shippingModel.setReceiverNameByKorea(data[9].get("receiverNameByKorea").toString());
		shippingModel.setOwnerContent(data[10].get("setOwnerContent").toString());
		shippingModel.setReceiverNameByEnglish(data[11].get("receiverNameByEnglish").toString());
		
		shippingModel.setPrivateTransit(data[12].get("privateTransit").toString());
		shippingModel.setTransitNumber(data[13].get("transitNumber").toString());
		shippingModel.setAgreeWithCollection(data[14].get("agreeWithCollection").toString());
		
		shippingModel.setCallNumberFront(data[15].get("callNumberFront").toString());
		shippingModel.setCallNumberMiddle(data[16].get("callNumberMiddle").toString());
		shippingModel.setCallNumberRear(data[17].get("callNumberRear").toString());
		
		shippingModel.setPostCode(data[18].get("postCode").toString());
		shippingModel.setDeliveryAddress(data[19].get("deliveryAddress").toString());
		shippingModel.setDetailAddress(data[20].get("detailAddress").toString());
		shippingModel.setDeliveryMessage(data[21].get("deliveryMessage").toString());
		
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

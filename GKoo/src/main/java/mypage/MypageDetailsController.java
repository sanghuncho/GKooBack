package mypage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gkoo.configuration.SecurityConfig;
import mypage.information.ProductsCommonInformation;
import mypage.information.RecipientData;
import serviceBase.ServicePath;
import mypage.information.ProductsInformation.Product;
import util.AuthentificationService;

@RestController
public class MypageDetailsController {

	@CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
	@RequestMapping("/orderingpersoninfo")
	public OrderingPersonInformation requestOrderingpersonInfo(HttpServletRequest request) throws SQLException  {
		MypageDetailsImpl detailsImp = new MypageDetailsImpl();
		String fullname = AuthentificationService.getAuthenficatedFullname(request);
		/*ToDo : low coupling - Spring injection, interface, injection */
		return detailsImp.getOrderingpersonInfo(fullname);
	}
	
	@CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
	@RequestMapping("/recipientinfo/{orderid}")
	public RecipientData requestRecipientInfo(HttpServletRequest request, @PathVariable String orderid) throws SQLException  {
		MypageDetailsImpl detailsImp = new MypageDetailsImpl();
        String userid = SecurityConfig.getUserid(request);      
		/*ToDo : low coupling - Spring injection, interface, injection */
		return detailsImp.getRecipientInfo(userid, orderid);
	}
	
	@CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
	@RequestMapping("/productscommoninfo/{number}")
	public ProductsCommonInformation requestProductsCommonInfo(HttpServletRequest request, @PathVariable String number) throws SQLException  {
		MypageDetailsImpl detailsImp = new MypageDetailsImpl();
		String userid = SecurityConfig.getUserid(request);		
		/*ToDo : low coupling - Spring injection, interface, injection */
		return detailsImp.getProductsCommonInfo(userid, number);
	}
	
	@CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
	@RequestMapping("/productslistinfo/{number}")
	public List<Product> requestProductsListInfo(HttpServletRequest request, @PathVariable String number) throws SQLException  {
		MypageDetailsImpl detailsImp = new MypageDetailsImpl();
		String memberId = AuthentificationService.getAuthenficatedMemberID(request);		
		/*ToDo : low coupling - Spring injection, interface, injection */
		return detailsImp.getProductsInfo(memberId, number);
	}
	
//	@CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
//	@RequestMapping(value = "/willpaydeleveryfee", method = RequestMethod.POST)
//	public ResponseEntity<?> willPayDeliveryFee(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException {
//		MypageDetailsImpl detailsImp = new MypageDetailsImpl();
//		String memberId = AuthentificationService.getAuthenficatedMemberID(request);
//        System.out.println("oderNumber: " + data[0].get("orderNumber"));
//        System.out.println("ownerNumber: " + data[1].get("ownerName"));
//        String orderNumber = data[0].get("orderNumber").toString();
//        String ownerName = data[1].get("ownerName").toString();
//        detailsImp.willPayDeliveryFee(memberId, orderNumber, ownerName);
//		HttpHeaders headers = new HttpHeaders();
//		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
//	}
	
	@CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
	@RequestMapping(value = "/willpaydeleveryfeeupdate", method = RequestMethod.POST)
	public ProductsCommonInformation willPayDeliveryFeeUpdate(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException {
		MypageDetailsImpl detailsImp = new MypageDetailsImpl();
		String memberId = AuthentificationService.getAuthenficatedMemberID(request);
        System.out.println("oderNumber: " + data[0].get("orderNumber"));
        System.out.println("ownerNumber: " + data[1].get("ownerName"));
        String orderNumber = data[0].get("orderNumber").toString();
        String ownerName = data[1].get("ownerName").toString();
        detailsImp.willPayDeliveryFee(memberId, orderNumber, ownerName);
		return detailsImp.getProductsCommonInfo(memberId, orderNumber);
	}
	
	@CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
    @RequestMapping(value = "/updaterecipientdata", method = RequestMethod.POST)
    public ResponseEntity<?> updateRecipientData(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException  {
        MypageDetailsImpl detailsImp = new MypageDetailsImpl();
        String userid = SecurityConfig.getUserid(request);
        //RecipientData recipientData = detailsImp.createRecipientData(memberId, data);
        /*ToDo : low coupling - Spring injection, interface, injection */
        return detailsImp.updateRecipientData(userid, data);
    }
	
	@CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
    @RequestMapping(value = "/updateDataEditorProductsList", method = RequestMethod.POST)
    public ResponseEntity<?> updateDataEditorProductsList(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException, JsonParseException, JsonMappingException, IOException  {
        MypageDetailsImpl detailsImp = new MypageDetailsImpl();
        String userid = SecurityConfig.getUserid(request);        //RecipientData recipientData = detailsImp.createRecipientData(memberId, data);
        /*ToDo : low coupling - Spring injection, interface, injection */
        return detailsImp.updateDataEditorProductsList(userid, data);
    }
	
	@CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
    @RequestMapping(value = "/deleteShipingServiceData", method = RequestMethod.POST)
    public ResponseEntity<?> deleteShipingServiceData(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException, JsonParseException, JsonMappingException, IOException  {
        MypageDetailsImpl detailsImp = new MypageDetailsImpl();
        String memberId = AuthentificationService.getAuthenficatedMemberID(request);
        return detailsImp.deleteShipingServiceData(memberId, data);
    }
}
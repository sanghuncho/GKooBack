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
import com.gkoo.data.BuyingServiceDetailData;
import com.gkoo.data.RecipientData;
import mypage.information.ProductsCommonInformation;
import serviceBase.ServicePath;
import mypage.information.ProductsInformation.Product;
import util.AuthentificationService;

@RestController
public class MypageDetailsController {

    private MypageDetailsImpl detailsImp;
    public MypageDetailsController(MypageDetailsImpl detailsImp) {
        this.detailsImp = detailsImp;
    }
    
    //direct return in react
	@CrossOrigin(origins = {ServicePath.DETAILS_MYPAGE_DEV, ServicePath.DETAILS_MYPAGE_PROD})
	@RequestMapping("/orderingpersoninfo")
	public OrderingPersonInformation requestOrderingpersonInfo(HttpServletRequest request) throws SQLException  {
		
		String fullname = AuthentificationService.getAuthenficatedFullname(request);
		/*ToDo : low coupling - Spring injection, interface, injection */
		return detailsImp.getOrderingpersonInfo(fullname);
	}
	
	@CrossOrigin(origins = {ServicePath.DETAILS_MYPAGE_DEV, ServicePath.DETAILS_MYPAGE_PROD})
	@RequestMapping("/recipientinfo/{orderid}/{userid}")
	public RecipientData requestRecipientInfo(HttpServletRequest request, @PathVariable String orderid, @PathVariable String userid) throws SQLException  {
		/*ToDo : low coupling - Spring injection, interface, injection */
		return detailsImp.getRecipientInfo(userid, orderid);
	}
	
	@CrossOrigin(origins = {ServicePath.DETAILS_MYPAGE_DEV, ServicePath.DETAILS_MYPAGE_PROD})
	@RequestMapping("/productscommoninfo/{orderid}/{userid}")
	public ProductsCommonInformation requestProductsCommonInfo(HttpServletRequest request, @PathVariable String orderid, @PathVariable String userid) throws SQLException  {
		/*ToDo : low coupling - Spring injection, interface, injection */
		return detailsImp.getProductsCommonInfo(userid, orderid);
	}
	
	@CrossOrigin(origins = {ServicePath.DETAILS_MYPAGE_DEV, ServicePath.DETAILS_MYPAGE_PROD})
	@RequestMapping("/productslistinfo/{orderid}/{userid}")
	public List<Product> requestProductsListInfo(HttpServletRequest request, @PathVariable String orderid, @PathVariable String userid) throws SQLException  {
		//String memberId = AuthentificationService.getAuthenficatedMemberID(request);		
		/*ToDo : low coupling - Spring injection, interface, injection */
		return detailsImp.getProductsInfo(userid, orderid);
	}
	
	@CrossOrigin(origins = {ServicePath.DETAILS_MYPAGE_DEV, ServicePath.DETAILS_MYPAGE_PROD})
    @RequestMapping("/mypageDetailData/{orderid}/{userid}")
    public MypageDetailData requestMypageDetailData(HttpServletRequest request, @PathVariable String userid, @PathVariable String orderid) throws SQLException  {
        return detailsImp.getMypageDetailData(userid, orderid);
    }
	
	@CrossOrigin(origins = {ServicePath.DETAILS_MYPAGE_DEV, ServicePath.DETAILS_MYPAGE_PROD})
	@RequestMapping(value = "/updatePaymentOwnernameShippingService/{userid}", method = RequestMethod.POST)
	public ProductsCommonInformation willPayDeliveryFeeUpdate(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) throws SQLException {
        String orderid = data[0].get("orderid").toString();
        String paymentOwnername = data[1].get("paymentOwnername").toString();
        String paymentArtStr = data[2].get("paymentArt").toString();
        detailsImp.willPayDeliveryFee(userid, orderid, paymentOwnername, Integer.parseInt(paymentArtStr));
		return detailsImp.getProductsCommonInfo(userid, orderid);
	}
	
	@CrossOrigin(origins = {ServicePath.DETAILS_MYPAGE_DEV, ServicePath.DETAILS_MYPAGE_PROD})
    @RequestMapping(value = "/updaterecipientdata/{userid}", method = RequestMethod.POST)
    public ResponseEntity<?> updateRecipientData(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        //RecipientData recipientData = detailsImp.createRecipientData(memberId, data);
        /*ToDo : low coupling - Spring injection, interface, injection */
        return detailsImp.updateRecipientData(userid, data);
    }
	
	@CrossOrigin(origins = {ServicePath.DETAILS_MYPAGE_DEV, ServicePath.DETAILS_MYPAGE_PROD})
    @RequestMapping(value = "/updateDataEditorProductsList/{userid}", method = RequestMethod.POST)
    public ResponseEntity<?> updateDataEditorProductsList(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) throws SQLException, JsonParseException, JsonMappingException, IOException  {
        /*ToDo : low coupling - Spring injection, interface, injection */
        return detailsImp.updateDataEditorProductsList(userid, data);
    }
	
	@CrossOrigin(origins = {ServicePath.DETAILS_MYPAGE_DEV, ServicePath.DETAILS_MYPAGE_PROD})
    @RequestMapping(value = "/deleteShipingServiceData/{userid}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteShipingServiceData(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) throws SQLException, JsonParseException, JsonMappingException, IOException  {
        return detailsImp.deleteShipingServiceData(userid, data);
    }
	
    /////////////////////
	/// MyPage        ///
    /// BuyingService ///
    /////////////////////
	@CrossOrigin(origins = {ServicePath.DETAILS_MYPAGE_BUYINGSERVICE_DEV, ServicePath.DETAILS_MYPAGE_BUYINGSERVICE_PROD})
	@RequestMapping("/mypageBuyingServiceDetailData/{orderid}/{userid}")
    public BuyingServiceDetailData requestMypageBuyingServiceDetailData(HttpServletRequest request, @PathVariable String userid, @PathVariable String orderid) throws SQLException  {
        return detailsImp.getMypageBuyingServiceDetailData(userid, orderid);
    }
	
	//share react component @see updateRecipientData
	@CrossOrigin(origins = {ServicePath.DETAILS_MYPAGE_BUYINGSERVICE_DEV, ServicePath.DETAILS_MYPAGE_BUYINGSERVICE_PROD})
	@RequestMapping(value = "/updateRecipientdataBuyingService/{orderid}/{userid}", method = RequestMethod.POST)
    public ResponseEntity<?> updateRecipientdataBuyingService(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        return detailsImp.updateRecipientdataBuyingService(userid, data);
    }
	
	@CrossOrigin(origins = {ServicePath.DETAILS_MYPAGE_BUYINGSERVICE_DEV, ServicePath.DETAILS_MYPAGE_BUYINGSERVICE_PROD})
	@RequestMapping(value = "/getRecipientDataBuyingService/{orderid}/{userid}")
    public RecipientData getRecipientDataBuyingService(HttpServletRequest request, @PathVariable String orderid, @PathVariable String userid) throws SQLException  {
        return detailsImp.getRecipientDataBuyingService(userid, orderid);
    }
}
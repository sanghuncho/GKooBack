package mypage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mypage.information.ProductsCommonInformation;
import mypage.information.RecipientInformation;
import mypage.information.ProductsInformation.Product;
import util.AuthentificationService;

@RestController
public class MypageDetailsController {

	@CrossOrigin(origins = "http://localhost:3000/detailsmypage")
	@RequestMapping("/orderingpersoninfo")
	public OrderingPersonInformation requestOrderingpersonInfo(HttpServletRequest request) throws SQLException  {
		MypageDetailsImpl detailsImp = new MypageDetailsImpl();
		String fullname = AuthentificationService.getAuthenficatedFullname(request);
		/*ToDo : low coupling - Spring injection, interface, injection */
		return detailsImp.getOrderingpersonInfo(fullname);
	}
	
	@CrossOrigin(origins = "http://localhost:3000/detailsmypage")
	@RequestMapping("/recipientinfo/{number}")
	public RecipientInformation requestRecipientInfo(HttpServletRequest request, @PathVariable String number) throws SQLException  {
		MypageDetailsImpl detailsImp = new MypageDetailsImpl();
		String memberId = AuthentificationService.getAuthenficatedMemberID(request);		
		/*ToDo : low coupling - Spring injection, interface, injection */
		return detailsImp.getRecipientInfo(memberId, number);
	}
	
	@CrossOrigin(origins = "http://localhost:3000/detailsmypage")
	@RequestMapping("/productscommoninfo/{number}")
	public ProductsCommonInformation requestProductsCommonInfo(HttpServletRequest request, @PathVariable String number) throws SQLException  {
		MypageDetailsImpl detailsImp = new MypageDetailsImpl();
		String memberId = AuthentificationService.getAuthenficatedMemberID(request);		
		/*ToDo : low coupling - Spring injection, interface, injection */
		return detailsImp.getProductsCommonInfo(memberId, number);
	}
	
	@CrossOrigin(origins = "http://localhost:3000/detailsmypage")
	@RequestMapping("/productslistinfo/{number}")
	public List<Product> requestProductsListInfo(HttpServletRequest request, @PathVariable String number) throws SQLException  {
		MypageDetailsImpl detailsImp = new MypageDetailsImpl();
		String memberId = AuthentificationService.getAuthenficatedMemberID(request);		
		/*ToDo : low coupling - Spring injection, interface, injection */
		return detailsImp.getProductsInfo(memberId, number);
	}
	
	@CrossOrigin(origins = "http://localhost:3000/detailsmypage")
	@RequestMapping(value = "/willpaydeleveryfee", method = RequestMethod.POST)
	public ResponseEntity<?> willPayDeliveryFee(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException {
		MypageDetailsImpl detailsImp = new MypageDetailsImpl();
		String memberId = AuthentificationService.getAuthenficatedMemberID(request);
        System.out.println("oderNumber: " + data[0].get("orderNumber"));
        System.out.println("ownerNumber: " + data[1].get("ownerName"));
        String orderNumber = data[0].get("orderNumber").toString();
        String ownerName = data[1].get("ownerName").toString();
        detailsImp.willPayDeliveryFee(memberId, orderNumber, ownerName);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
}

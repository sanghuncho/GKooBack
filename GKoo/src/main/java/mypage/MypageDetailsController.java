package mypage;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
}

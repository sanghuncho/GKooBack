package mypage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerStatusController {
	public CustomerStatusController(){}

	@CrossOrigin(origins = "http://localhost:3000/mypage")
	@RequestMapping("/customerstatus")
	public CustomerStatus requestCustomerStatus(HttpServletRequest request) throws SQLException {
        AccessToken accessToken = ((KeycloakPrincipal<?>) request.getUserPrincipal())
        	    .getKeycloakSecurityContext().getToken();
 
		CustomerStatusDAO statusDAO = new CustomerStatusDAO();
		statusDAO.checkGkooId(accessToken);
		
		String memberId = accessToken.getPreferredUsername();
		return statusDAO.getCustomerStatusFromDB(memberId);
	}
}

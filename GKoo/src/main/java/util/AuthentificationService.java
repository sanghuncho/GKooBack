package util;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;

public class AuthentificationService {

	public static String getAuthenficatedMemberID(HttpServletRequest request) {
		AccessToken accessToken = ((KeycloakPrincipal<?>) request.getUserPrincipal())
        	    .getKeycloakSecurityContext().getToken();
		return accessToken.getPreferredUsername();
	}
	
	public static String getAuthenficatedFullname(HttpServletRequest request) {
		AccessToken accessToken = ((KeycloakPrincipal<?>) request.getUserPrincipal())
        	    .getKeycloakSecurityContext().getToken();
		/*ToDO: null check*/
		String fullname = accessToken.getFamilyName() +""+ accessToken.getGivenName();
		return fullname;
	}
}

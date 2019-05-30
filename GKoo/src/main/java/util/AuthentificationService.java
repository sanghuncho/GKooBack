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
}

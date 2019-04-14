package util;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;

public class MemberProfile {

	public static final String getMemberID(HttpServletRequest request) {
		AccessToken accessToken = ((KeycloakPrincipal<?>) request.getUserPrincipal())
        	    .getKeycloakSecurityContext().getToken();
        String username = accessToken.getPreferredUsername();
		return username;
	}
}

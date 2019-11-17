package com.gkoo.configuration;

import javax.servlet.http.HttpServletRequest;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import com.gkoo.exception.ConfigException;

public class SecurityConfig {

    public static AccessToken getAccessToken(HttpServletRequest request) throws ConfigException {
        try {
            return ((KeycloakPrincipal<?>) request.getUserPrincipal())
                    .getKeycloakSecurityContext().getToken();
        } catch (ConfigException e) {
            throw new ConfigException("access token has error:" + request, e);
        }
    }
    
    public static String getUserid(HttpServletRequest request) throws ConfigException {
        try {
            return ((KeycloakPrincipal<?>) request.getUserPrincipal())
                    .getKeycloakSecurityContext().getToken().getPreferredUsername();
        } catch (ConfigException e) {
            throw new ConfigException("error retrieving userid :" + request, e);
        }
    }
}

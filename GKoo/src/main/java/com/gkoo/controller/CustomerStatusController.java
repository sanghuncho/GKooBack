package com.gkoo.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gkoo.configuration.AppConfig;
import com.gkoo.configuration.SecurityConfig;
import com.gkoo.data.CustomerStatus;
import com.gkoo.service.CustomerStatusService;
import serviceBase.ServicePath;

/**
 *
 * @author sanghuncho
 *
 * @since  12.11.2019
 *
 */
@RestController
public class CustomerStatusController {
    
    private final CustomerStatusService customerstatusService;
    
    @Autowired
    public CustomerStatusController(CustomerStatusService customerstatusService){
        this.customerstatusService = customerstatusService;
    }

	@CrossOrigin(origins = ServicePath.MYPAGE)
	@RequestMapping("/customerstatus")
	public CustomerStatus requestCustomerStatus(HttpServletRequest request) throws SQLException {
        AccessToken accessToken = SecurityConfig.getAccessToken(request);
        customerstatusService.checkUserid(accessToken);
        return customerstatusService.getCustomerStatus(accessToken);
	}
}

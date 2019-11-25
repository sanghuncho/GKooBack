package com.gkoo.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gkoo.configuration.AppConfig;
import com.gkoo.configuration.SecurityConfig;
import com.gkoo.data.CustomerStatus;
import com.gkoo.data.UserBaseInfo;
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
    private static final Logger LOGGER = LogManager.getLogger();
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
	
	@CrossOrigin(origins = ServicePath.MYPAGE)
    @RequestMapping("/fetchuserbaseinfo")
    public UserBaseInfo requestUserBaseInfo(HttpServletRequest request) throws SQLException {
	    String userid = SecurityConfig.getUserid(request);
        return customerstatusService.getUserBaseInfo(userid);
    }
	
	@CrossOrigin(origins = ServicePath.MYPAGE)
    @RequestMapping("/updateuserbaseinfo")
    public ResponseEntity<?> updateBaseInfo(HttpServletRequest request) throws SQLException {
        String userid = SecurityConfig.getUserid(request);
        HttpHeaders headers = new HttpHeaders();
        LOGGER.info("updateuserbaseinfo");
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
}
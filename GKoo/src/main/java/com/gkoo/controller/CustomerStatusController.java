package com.gkoo.controller;

import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
        String userid = SecurityConfig.getUserid(request);
        return customerstatusService.getCustomerStatus(userid);
	}
	
	@CrossOrigin(origins = ServicePath.MYPAGE)
    @RequestMapping("/fetchuserbaseinfo")
    public UserBaseInfo requestUserBaseInfo(HttpServletRequest request) throws SQLException {
	    String userid = SecurityConfig.getUserid(request);
        return customerstatusService.getUserBaseInfo(userid);
    }
	
	@CrossOrigin(origins = ServicePath.MYPAGE)
    @RequestMapping(value = "/updateuserbaseinfo", method = RequestMethod.POST)
    public ResponseEntity<?> updateBaseInfo(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException {
        String userid = SecurityConfig.getUserid(request);
        LOGGER.info("updateuserbaseinfo");
        return customerstatusService.updateBaseInfo(data, userid);
    }
}
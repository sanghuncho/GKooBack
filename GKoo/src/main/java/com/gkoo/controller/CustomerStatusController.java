package com.gkoo.controller;

import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    
    @CrossOrigin(origins = {ServicePath.MYPAGE_DEV, ServicePath.MYPAGE_PROD})
    @RequestMapping(value = "/registerinitialcustomer/{userid}", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public ResponseEntity<String> registerInitialCustomer(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) throws SQLException {
        String lastname= data[0].get("lastname").toString();
        String firstname= data[1].get("firstname").toString();
        //later test : AccessToken accessToken = SecurityConfig.getAccessToken(request);
        return customerstatusService.checkUserid(userid, lastname, firstname);
    }

    @CrossOrigin(origins = {ServicePath.MYPAGE_DEV, ServicePath.MYPAGE_PROD})
    @RequestMapping("/customerstatus/{userid}")
    public CustomerStatus requestCustomerStatus(HttpServletRequest request, @PathVariable String userid) throws SQLException {
        return customerstatusService.getCustomerStatus(userid);
	}
    
	@CrossOrigin(origins = {ServicePath.MYPAGE_DEV, ServicePath.MYPAGE_PROD})
    @RequestMapping("/fetchuserbaseinfo/{userid}")
    public UserBaseInfo requestUserBaseInfo(HttpServletRequest request, @PathVariable String userid) throws SQLException {
	    //String userid = SecurityConfig.getUserid(request);
        return customerstatusService.getUserBaseInfo(userid);
    }
	
	@CrossOrigin(origins = {ServicePath.MYPAGE_DEV, ServicePath.MYPAGE_PROD})
    @RequestMapping(value = "/updateuserbaseinfo/{userid}", method = RequestMethod.POST)
    public ResponseEntity<?> updateBaseInfo(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) throws SQLException {
        //String userid = SecurityConfig.getUserid(request);
        LOGGER.info("updateuserbaseinfo");
        return customerstatusService.updateBaseInfo(data, userid);
    }
}
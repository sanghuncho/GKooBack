package com.gkoo.controller;

import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    
    //@CrossOrigin(origins = {ServicePath.MYPAGE_DEV, ServicePath.MYPAGE_PROD})
    //@CrossOrigin(origins = "http://localhost:3000")
    @CrossOrigin(origins = "*")
    //@RequestMapping(value = "/registerinitialcustomer/{userid}", method = RequestMethod.POST)
    @RequestMapping(value = "/registerinitialcustomer", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    //public ResponseEntity<?> registerInitialCustomer(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) throws SQLException {
    public CustomerStatus registerInitialCustomer(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException {
//        String lastname= data[0].get("lastname").toString();
//        String firstname= data[1].get("firstname").toString();
//        return customerstatusService.checkUserid(userid, lastname, firstname);
        String message = "test";
        ResponseEntity<String> response = new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
        //response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        CustomerStatus customer = new CustomerStatus("m", 100, 100, 100, "GK10");
        return customer;
        
    }

    //@CrossOrigin(origins = {ServicePath.MYPAGE_DEV, ServicePath.MYPAGE_PROD})
    //@CrossOrigin(origins = "http://localhost:3000")
    @CrossOrigin(origins = "*")
    //@RequestMapping(value = "/customerstatus/{userid}", method = RequestMethod.POST)
    //@CrossOrigin(origins = "http://localhost:3000/mypage")
    @RequestMapping("/customerstatus/{userid}")
    //public CustomerStatus requestCustomerStatus(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) throws SQLException {
    public CustomerStatus requestCustomerStatus(HttpServletRequest request, @PathVariable String userid) throws SQLException {
        //String lastname= data[0].get("lastname").toString();
        //String firstname= data[1].get("firstname").toString();
        //customerstatusService.checkUserid(userid, lastname, firstname);
        return customerstatusService.getCustomerStatus(userid);
//        CustomerStatus customer = new CustomerStatus("m", 100, 100, 100, "GK10");
//        return customer;
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
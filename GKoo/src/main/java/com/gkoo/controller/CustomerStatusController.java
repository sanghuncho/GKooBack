package com.gkoo.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gkoo.data.CustomerStatus;

/**
 *
 * @author sanghuncho
 *
 * @since  12.11.2019
 *
 */
@RestController
public class CustomerStatusController {
    
    //private final CustomerStatusService customerstatusService;
    //private static final Logger LOGGER = LogManager.getLogger();
    public CustomerStatusController(){}
//    @Autowired
//    public CustomerStatusController(CustomerStatusService customerstatusService){
//        this.customerstatusService = customerstatusService;
//    }

	//@CrossOrigin(origins = ServicePath.MYPAGE)
	//@RequestMapping("/customerstatus/{userid}")
	//@RequestMapping("/customerstatus")
	//@RequestMapping(value = "/customerstatus/{userid}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	//public CustomerStatus requestCustomerStatus(HttpServletRequest request, @PathVariable String userid) throws SQLException {
//	public CustomerStatus requestCustomerStatus(HttpServletRequest request) throws SQLException {
//        AccessToken accessToken = SecurityConfig.getAccessToken(request);
//        customerstatusService.checkUserid(accessToken);
//        String userid = SecurityConfig.getUserid(request);    
//        return customerstatusService.getCustomerStatus(userid);
//	}
	
	@CrossOrigin(origins = "http://localhost:3000/verwalter")
    @RequestMapping("/testcustomer")
    public CustomerStatus requestTestCustomer(HttpServletRequest request) {
	    //LOGGER.info("test customer" + customerstatusService.getTestCustomer());
	    //LOGGER.info("test customer called");
        //return customerstatusService.getTestCustomer();
	    return new CustomerStatus("m",1000, 10, 10);
    }
}
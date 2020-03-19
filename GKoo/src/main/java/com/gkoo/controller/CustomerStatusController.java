package com.gkoo.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gkoo.configuration.SecurityConfig;
import com.gkoo.data.CustomerStatus;
import com.gkoo.data.UserBaseInfo;
import com.gkoo.service.CustomerStatusService;
import mypage.MypageDetailData;
import serviceBase.ServicePath;
import util.AuthentificationService;

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

    //production
	@CrossOrigin(origins = "http://gkoo.co.kr/mypage")
	//@CrossOrigin(origins = ServicePath.MYPAGE)
    //@CrossOrigin(origins = "https://www.gkoo.co.kr/mypage")
    //@RequestMapping("/gkoo/customerstatus")
    //@CrossOrigin(origins = "http://localhost:3000")
    @CrossOrigin(origins = "https://www.gkoo.co.kr/mypage")
    @RequestMapping("/gkoo/customerstatus/{userid}")
	public CustomerStatus requestCustomerStatus(HttpServletRequest request, @PathVariable String userid) throws SQLException {
	//public CustomerStatus requestCustomerStatus(HttpServletRequest request) throws SQLException {    
	    //AccessToken accessToken = SecurityConfig.getAccessToken(request);
        //customerstatusService.checkUserid(accessToken);
        //String userid = SecurityConfig.getUserid(request);
        return customerstatusService.getCustomerStatus(userid);
        //return new CustomerStatus(userid, 1, 1, 1);
        //return new CustomerStatus("test", 1, 1, 1);
        
	}
    
//    @RequestMapping("/mypageDetailData/{orderid}")
//    public MypageDetailData requestMypageDetailData(HttpServletRequest request, @PathVariable String orderid) throws SQLException  {
//        String userid = AuthentificationService.getAuthenficatedMemberID(request);        
//        return detailsImp.getMypageDetailData(userid, orderid);
//    }
	
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
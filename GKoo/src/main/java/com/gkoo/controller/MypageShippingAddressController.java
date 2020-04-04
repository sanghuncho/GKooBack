package com.gkoo.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gkoo.data.CustomerStatus;
import com.gkoo.service.MypageShippingAddressService;
import serviceBase.ServicePath;

/**
 * @author sanghuncho
 *
 */
@RestController
public class MypageShippingAddressController {
    
    private final MypageShippingAddressService mypageShippingAddressService;
    private CustomerStatus customerStatus;
    
    @Autowired
    public MypageShippingAddressController(MypageShippingAddressService mypageShippingAddressService, CustomerStatus customerStatus) {
        this.mypageShippingAddressService = mypageShippingAddressService;
        this.customerStatus = customerStatus;
    }
    
    @CrossOrigin(origins = {ServicePath.MYPAGE_SHIPPING_ADDRESS_DEV, ServicePath.MYPAGE_SHIPPING_ADDRESS_PROD})
    @RequestMapping("/getPersonalBoxAddress/{userid}")
    public CustomerStatus requestPersonalBoxAddress(HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        String personalBoxAddress = mypageShippingAddressService.getPersonalBoxAddress(userid);
        customerStatus.setPersonalBoxAddress(personalBoxAddress);
        return customerStatus;
    }
}

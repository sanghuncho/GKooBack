package com.gkoo.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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
import com.gkoo.data.FavoriteAddress;
import com.gkoo.data.UserBaseInfo;
import com.gkoo.db.AddressManagerDB;
import com.gkoo.service.AddressManagerService;
import com.gkoo.service.ShippingService;
import serviceBase.ServicePath;

/**
 * @author sanghuncho
 *
 */
@RestController
public class ShippingServiceController {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private final ShippingService shippingService;
    private final AddressManagerService addressManagerService;

    @Autowired
    public ShippingServiceController(ShippingService shippingService, AddressManagerService addressManagerService) {
        this.shippingService = shippingService;
        this.addressManagerService = addressManagerService;
    }
    
    @CrossOrigin(origins = {ServicePath.SHIPPING_SERVICE_DEV, ServicePath.SHIPPING_SERVICE_PROD})
    @RequestMapping(value = "/createshippingservice/{userid}",  method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public ResponseEntity<?> requestShippingservice(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) {        
        return shippingService.requestShippingservice(data, userid);
    }
    
    @CrossOrigin(origins = {ServicePath.SHIPPING_SERVICE_DEV, ServicePath.SHIPPING_SERVICE_PROD})
    @RequestMapping("/fetchcustomerbaseinfo/{userid}")
    public UserBaseInfo requestCustomerBaseInfo(HttpServletRequest request, @PathVariable String userid) throws SQLException {
        return shippingService.getUserBaseInfo(userid);
    }
    
    @CrossOrigin(origins = {ServicePath.SHIPPING_SERVICE_DEV, ServicePath.SHIPPING_SERVICE_PROD})
    @RequestMapping(value = "/registerFavoriteAddress/{userid}", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public ResponseEntity<?> registerFavoriteAddress(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) {
        return shippingService.registerFavoriteAddress(data, userid);
    }
    
    @CrossOrigin(origins = {ServicePath.SHIPPING_SERVICE_DEV, ServicePath.SHIPPING_SERVICE_PROD})
    @RequestMapping(value = "/retrieveFavoriteAddressList/{userid}")
    public List<FavoriteAddress> retriveFavoriteAddressList(HttpServletRequest request, @PathVariable String userid) {
        return AddressManagerDB.getFavoriteAddressList(userid);
    }
}
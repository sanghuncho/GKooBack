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
import com.gkoo.configuration.SecurityConfig;
import com.gkoo.data.ConfigurationData;
import com.gkoo.data.EstimationService;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.data.UserBaseInfo;
import com.gkoo.db.AddressManagerDB;
import com.gkoo.service.BuyingService;
import serviceBase.ServicePath;

/**
 *
 * @author sanghuncho
 *
 * @since  07.01.2020
 *
 */
@RestController
public class BuyingServiceController {
    private static final Logger LOGGER = LogManager.getLogger();
    private final BuyingService buyingService;
    private final ConfigurationData configurationData;
    
    @Autowired
    public BuyingServiceController(BuyingService buyingService, ConfigurationData config) {
        this.buyingService = buyingService;
        this.configurationData = config;
    }
    
    //@CrossOrigin(origins = {ServicePath.BUYING_SERVICE_DEV, ServicePath.BUYING_SERVICE_PROD})
    @RequestMapping(value = "/fastEstimationBuyingService", method = RequestMethod.POST)
    public EstimationService requestFastEstimationBuyingService(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) {        
        String userid = SecurityConfig.getUserid(request);      
        return buyingService.fastEstimationBuyingService(data, userid);
    }
    
    @CrossOrigin(origins = {ServicePath.BUYING_SERVICE_DEV, ServicePath.BUYING_SERVICE_PROD})
    @RequestMapping(value = "/estimationBuyingService/{userid}", method = RequestMethod.POST)
    public EstimationService requestEstimationBuyingService(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) {        
        //String userid = SecurityConfig.getUserid(request);      
        return buyingService.estimationBuyingService(data, userid);
    }
    
    @CrossOrigin(origins = {ServicePath.BUYING_SERVICE_DEV, ServicePath.BUYING_SERVICE_PROD})
    @RequestMapping(value = "/createBuyingService/{userid}", method = RequestMethod.POST)
    public ResponseEntity<?> createBuyingService(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) {        
        //String userid = SecurityConfig.getUserid(request);      
        return buyingService.createBuyingService(data, userid);
    }
    
    @CrossOrigin(origins = {ServicePath.BUYING_SERVICE_DEV, ServicePath.BUYING_SERVICE_PROD})
    @RequestMapping(value = "/fetchFavoriteAddressList/{userid}")
    public List<FavoriteAddress> getFavoriteAddressList(HttpServletRequest request, @PathVariable String userid) {
        //String userid = SecurityConfig.getUserid(request);
        return AddressManagerDB.getFavoriteAddressList(userid);
    }
    
    @CrossOrigin(origins = {ServicePath.BUYING_SERVICE_DEV, ServicePath.BUYING_SERVICE_PROD})
    @RequestMapping("/fetchcustomerbaseinfoBuyingService/{userid}")
    public UserBaseInfo requestCustomerBaseInfo(HttpServletRequest request, @PathVariable String userid) throws SQLException {
        //String userid = SecurityConfig.getUserid(request);
        return buyingService.getUserBaseInfo(userid);
    }
    
    @CrossOrigin(origins = {ServicePath.BUYING_SERVICE_DEV, ServicePath.BUYING_SERVICE_PROD})
    @RequestMapping(value = "/registerFavoriteAddressBuyingService/{userid}", method = RequestMethod.POST)
    public ResponseEntity<?> registerFavoriteAddress(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) {
        //String userid = SecurityConfig.getUserid(request);
        return buyingService.registerFavoriteAddress(data, userid);
    }
}
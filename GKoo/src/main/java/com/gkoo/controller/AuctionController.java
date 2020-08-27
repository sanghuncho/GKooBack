package com.gkoo.controller;

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
import com.gkoo.service.AuctionService;
import serviceBase.ServicePath;

/**
 * @author sanghuncho
 * 
 * @since  27.08.2020
 *
 */
@RestController
public class AuctionController {
    private static final Logger LOGGER = LogManager.getLogger();
    private final AuctionService auctionService;
    
    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }
    
    @CrossOrigin(origins = {ServicePath.BUYING_SERVICE_DEV, ServicePath.BUYING_SERVICE_PROD})
    @RequestMapping(value = "/auctionBidService/{userid}", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public ResponseEntity<?> requestAuctionBidService(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) {        
        return auctionService.requestAuctionBidService(data, userid);
    }
    
}

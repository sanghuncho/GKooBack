package com.gkoo.controller;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gkoo.service.EmailSendService;
import serviceBase.ServicePath;

/**
 * @author sanghuncho
 * 
 * @since  31.08.2020
 *
 */
@RestController
public class EmailController {
    private static final Logger LOGGER = LogManager.getLogger();
    private final EmailSendService emailSendService;
    
    @Autowired
    public EmailController(EmailSendService emailSendService) {
        this.emailSendService = emailSendService;
    }
    
    @CrossOrigin(origins = {ServicePath.AUCTION_SERVICE_DEV, ServicePath.AUCTION_SERVICE_PROD})
    @RequestMapping(value = "/emailAuctionDeposit", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public ResponseEntity<?> requestAuctionDeposit(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) {        
        return emailSendService.requestAuctionDeposit(data);
    }
    
    @CrossOrigin(origins = {ServicePath.AUCTION_SERVICE_DEV, ServicePath.AUCTION_SERVICE_PROD})
    @RequestMapping(value = "/emailAuctionBid", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public ResponseEntity<?> requestAuctionBid(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) {        
        return emailSendService.requestAuctionBid(data);
    }
}

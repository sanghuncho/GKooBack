package com.gkoo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gkoo.service.MypageDetailBuyingService;
import serviceBase.ServicePath;

/**
 * @author sanghuncho
 *
 */
@RestController
public class MypageDetailBuyingServiceController {
    
    private MypageDetailBuyingService mypageDetailBuyingService;
    public MypageDetailBuyingServiceController(MypageDetailBuyingService mypageDetailBuyingService) {
        this.mypageDetailBuyingService = mypageDetailBuyingService;
    }
    
    @CrossOrigin(origins = {ServicePath.DETAILS_MYPAGE_BUYINGSERVICE_DEV, ServicePath.DETAILS_MYPAGE_BUYINGSERVICE_PROD})
    @RequestMapping(value = "/deleteBuyingServiceData", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public ResponseEntity<?> deleteBuyingServiceData(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException, JsonParseException, JsonMappingException, IOException  {
        String orderid = data[0].get("orderid").toString();
        return mypageDetailBuyingService.deleteBuyingServiceData(orderid);
    }
}

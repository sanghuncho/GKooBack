package com.gkoo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gkoo.configuration.SecurityConfig;
import com.gkoo.data.PersonalData;
import com.gkoo.service.MypageDetailService;
import mypage.MypageDetailsImpl;
import mypage.information.ProductsCommonInformation;
import mypage.information.RecipientData;
import mypage.information.ProductsInformation.Product;
import serviceBase.ServicePath;
import util.AuthentificationService;

/**
 *
 * @author sanghuncho
 *
 * @since  18.11.2019
 *
 */
@RestController
public class MypageDetailController {
    private final MypageDetailService mypageDetailService; 
    
    @Autowired
    public MypageDetailController(MypageDetailService mypageDetailService) {
        this.mypageDetailService = mypageDetailService;
    }
    
    @CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
    @RequestMapping("/orderingpersoninfo")
    public PersonalData requestOrderingpersonInfo(HttpServletRequest request) throws SQLException  {
        String fullname = SecurityConfig.getFullname(request);
        return mypageDetailService.getOrderingpersonInfo(fullname);
    }
    
    @CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
    @RequestMapping("/recipientinfo/{number}")
    public RecipientData requestRecipientInfo(HttpServletRequest request, @PathVariable String number) throws SQLException  {
        MypageDetailsImpl detailsImp = new MypageDetailsImpl();
        String memberId = AuthentificationService.getAuthenficatedMemberID(request);        
        return mypageDetailService.getRecipientInfo(memberId, number);
    }
    
    @CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
    @RequestMapping("/productscommoninfo/{number}")
    public ProductsCommonInformation requestProductsCommonInfo(HttpServletRequest request, @PathVariable String number) throws SQLException  {
        MypageDetailsImpl detailsImp = new MypageDetailsImpl();
        String memberId = AuthentificationService.getAuthenficatedMemberID(request);        
        return mypageDetailService.getProductsCommonInfo(memberId, number);
    }
    
    @CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
    @RequestMapping("/productslistinfo/{number}")
    public List<Product> requestProductsListInfo(HttpServletRequest request, @PathVariable String number) throws SQLException  {
        MypageDetailsImpl detailsImp = new MypageDetailsImpl();
        String memberId = AuthentificationService.getAuthenficatedMemberID(request);        
        return mypageDetailService.getProductsInfo(memberId, number);
    }
    
    @CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
    @RequestMapping(value = "/willpaydeleveryfeeupdate", method = RequestMethod.POST)
    public ProductsCommonInformation willPayDeliveryFeeUpdate(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException {
        MypageDetailsImpl detailsImp = new MypageDetailsImpl();
        String memberId = AuthentificationService.getAuthenficatedMemberID(request);
        String orderNumber = data[0].get("orderNumber").toString();
        String ownerName = data[1].get("ownerName").toString();
        detailsImp.willPayDeliveryFee(memberId, orderNumber, ownerName);
        return mypageDetailService.getProductsCommonInfo(memberId, orderNumber);
    }
    
    @CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
    @RequestMapping(value = "/updaterecipientdata", method = RequestMethod.POST)
    public ResponseEntity<?> updateRecipientData(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException  {
        MypageDetailsImpl detailsImp = new MypageDetailsImpl();
        String memberId = AuthentificationService.getAuthenficatedMemberID(request);
        return mypageDetailService.updateRecipientData(memberId, data);
    }
    
    @CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
    @RequestMapping(value = "/updateDataEditorProductsList", method = RequestMethod.POST)
    public ResponseEntity<?> updateDataEditorProductsList(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException, JsonParseException, JsonMappingException, IOException  {
        MypageDetailsImpl detailsImp = new MypageDetailsImpl();
        String memberId = AuthentificationService.getAuthenficatedMemberID(request);
        return mypageDetailService.updateDataEditorProductsList(memberId, data);
    }
    
    @CrossOrigin(origins = ServicePath.DETAILS_MYPAGE)
    @RequestMapping(value = "/deleteShipingServiceData", method = RequestMethod.POST)
    public ResponseEntity<?> deleteShipingServiceData(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException, JsonParseException, JsonMappingException, IOException  {
        MypageDetailsImpl detailsImp = new MypageDetailsImpl();
        String memberId = AuthentificationService.getAuthenficatedMemberID(request);
        return mypageDetailService.deleteShipingServiceData(memberId, data);
    }
}

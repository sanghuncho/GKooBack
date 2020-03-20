package com.gkoo.controller;

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
import com.gkoo.configuration.SecurityConfig;
import com.gkoo.data.BuyingOrderData;
import com.gkoo.data.DeliveryKoreaData;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
//import com.gkoo.configuration.SecurityConfig;
//import com.gkoo.data.OrderInformation;
//import com.gkoo.data.WarehouseInformation;
//import com.gkoo.service.MypageService;
//import mypage.OverviewInformationImpl;
//import serviceBase.ServicePath;
//import util.AuthentificationService;
import com.gkoo.service.MypageService;
import payment.PaymentData;
import serviceBase.ServicePath;

/**
 *
 * @author sanghuncho
 *
 * @since  12.11.2019
 *
 */
@RestController
public class MypageController {
    private final MypageService mypageService; 
    
    @Autowired
    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }
    
    ///////////////////////
    /// ShippingService ///
    ///////////////////////
    @CrossOrigin(origins = {ServicePath.MYPAGE_DEV, ServicePath.MYPAGE_PROD})
    @RequestMapping("/orderinformation/{userid}")
    public List<OrderInformation> requestOrderInformation(HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        //String userid = SecurityConfig.getUserid(request);        
        return mypageService.getOrderData(userid);
    }
    
    @CrossOrigin(origins = {ServicePath.MYPAGE_DEV, ServicePath.MYPAGE_PROD})
    @RequestMapping("/warehouseinformation/{userid}")
    public List<WarehouseInformation> requestWarehouseInformation(HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        //String userid = SecurityConfig.getUserid(request);
        return mypageService.getWarehouseData(userid);
    }
    
    @CrossOrigin(origins = {ServicePath.MYPAGE_DEV, ServicePath.MYPAGE_PROD})
    @RequestMapping("/paymentData/{userid}")
    public List<PaymentData> requestPaymentData(HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        //String userid = SecurityConfig.getUserid(request);
        return mypageService.getPaymentData(userid);
    }
    
    @CrossOrigin(origins = {ServicePath.MYPAGE_DEV, ServicePath.MYPAGE_PROD})
    @RequestMapping("/deliveryKoreaData/{userid}")
    public List<DeliveryKoreaData> requestDeliveryKoreaData(HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        //String userid = SecurityConfig.getUserid(request);
        return mypageService.getDeliveryKoreaData(userid);
    }
    
    @CrossOrigin(origins = {ServicePath.MYPAGE_DEV, ServicePath.MYPAGE_PROD})
    @RequestMapping(value = "/updatetrackingnumber/{userid}", method = RequestMethod.POST)
    public ResponseEntity<?> updateTrackingNumber(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        //String userid = SecurityConfig.getUserid(request);
        String orderNumber = data[0].get("orderid").toString();
        String trackingCompany = data[1].get("trackingCompany").toString();
        String trackingNumber = data[2].get("trackingNumber").toString();
        return mypageService.updateTrackingNumber(userid, orderNumber, trackingCompany, trackingNumber);
    }
    
    /////////////////////
    /// BuyingService ///
    /////////////////////
    @CrossOrigin(origins = {ServicePath.MYPAGE_BUYING_SERVICE_DEV, ServicePath.MYPAGE_BUYING_SERVICE_PROD})
    @RequestMapping("/orderdataBuyingServicer/{userid}")
    public List<BuyingOrderData> requestOrderDataBuyingService(HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        //String userid = SecurityConfig.getUserid(request);        
        return mypageService.getOrderDataBuyingService(userid);
    }
    
    @CrossOrigin(origins = {ServicePath.MYPAGE_BUYING_SERVICE_DEV, ServicePath.MYPAGE_BUYING_SERVICE_PROD})
    @RequestMapping("/paymentProductBuyingService/{userid}")
    public List<PaymentData> requestPaymentProductBuyingService(HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        //String userid = SecurityConfig.getUserid(request);
        return mypageService.getPaymentProductBuyingService(userid);
    }
    
    @CrossOrigin(origins = {ServicePath.MYPAGE_BUYING_SERVICE_DEV, ServicePath.MYPAGE_BUYING_SERVICE_PROD})
    @RequestMapping("/deliveryKoreaDataBuyingService/{userid}")
    public List<DeliveryKoreaData> requestDeliveryKoreaDataBuyingService(HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        //String userid = SecurityConfig.getUserid(request);
        return mypageService.getDeliveryKoreaDataBuyingService(userid);
    }
    
    @CrossOrigin(origins = {ServicePath.MYPAGE_BUYING_SERVICE_DEV, ServicePath.MYPAGE_BUYING_SERVICE_PROD})
    @RequestMapping("/updatePaymentProductBuyingService/{userid}")
    public ResponseEntity<?> updatePaymentProductBuyingService(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        //String userid = SecurityConfig.getUserid(request);
        return mypageService.updatePaymentProductBuyingService(data, userid);
    }
    
    @CrossOrigin(origins = {ServicePath.MYPAGE_BUYING_SERVICE_DEV, ServicePath.MYPAGE_BUYING_SERVICE_PROD})
    @RequestMapping("/updatePaymentDeliveryBuyingService/{userid}")
    public ResponseEntity<?> updatePaymentDeliveryBuyingService(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        //String userid = SecurityConfig.getUserid(request);
        return mypageService.updatePaymentDeliveryBuyingService(data, userid);
    }
    
    @CrossOrigin(origins = {ServicePath.MYPAGE_BUYING_SERVICE_DEV, ServicePath.MYPAGE_BUYING_SERVICE_PROD})
    @RequestMapping("/paymentDeliveryBuyingService/{userid}")
    public List<PaymentData> requestPaymentDeliveryBuyingService(HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        //String userid = SecurityConfig.getUserid(request);
        return mypageService.getPaymentDeliveryBuyingService(userid);
    }
}
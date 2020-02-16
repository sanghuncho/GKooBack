package com.gkoo.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gkoo.configuration.SecurityConfig;
import com.gkoo.data.BuyingOrderData;
import com.gkoo.data.DeliveryKoreaData;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.data.OrderInformation;
import com.gkoo.data.WarehouseInformation;
import com.gkoo.data.buyingservice.BuyingServiceData;
//import com.gkoo.configuration.SecurityConfig;
//import com.gkoo.data.OrderInformation;
//import com.gkoo.data.WarehouseInformation;
//import com.gkoo.service.MypageService;
//import mypage.OverviewInformationImpl;
//import serviceBase.ServicePath;
//import util.AuthentificationService;
import com.gkoo.service.MypageService;
import mypage.OverviewInformationImpl;
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
    //@CrossOrigin(origins = ServicePath.MYPAGE)
    @RequestMapping("/orderinformation")
    public List<OrderInformation> requestOrderInformation(HttpServletRequest request) throws SQLException  {
        String userid = SecurityConfig.getUserid(request);        
        return mypageService.getOrderData(userid);
    }
    
    @CrossOrigin(origins = ServicePath.MYPAGE)
    @RequestMapping("/warehouseinformation")
    public List<WarehouseInformation> requestWarehouseInformation(HttpServletRequest request) throws SQLException  {
        String userid = SecurityConfig.getUserid(request);
        return mypageService.getWarehouseData(userid);
    }
    
    @CrossOrigin(origins = ServicePath.MYPAGE)
    @RequestMapping("/paymentData")
    public List<PaymentData> requestPaymentData(HttpServletRequest request) throws SQLException  {
        String userid = SecurityConfig.getUserid(request);
        return mypageService.getPaymentData(userid);
    }
    
    @CrossOrigin(origins = ServicePath.MYPAGE)
    @RequestMapping("/deliveryKoreaData")
    public List<DeliveryKoreaData> requestDeliveryKoreaData(HttpServletRequest request) throws SQLException  {
        String userid = SecurityConfig.getUserid(request);
        return mypageService.getDeliveryKoreaData(userid);
    }
    
    @CrossOrigin(origins = ServicePath.MYPAGE)
    @RequestMapping(value = "/updatetrackingnumber", method = RequestMethod.POST)
    public ResponseEntity<?> updateTrackingNumber(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException  {
        String userid = SecurityConfig.getUserid(request);
        String orderNumber = data[0].get("orderid").toString();
        String trackingCompany = data[1].get("trackingCompany").toString();
        String trackingNumber = data[2].get("trackingNumber").toString();
        return mypageService.updateTrackingNumber(userid, orderNumber, trackingCompany, trackingNumber);
    }
    
    /////////////////////
    /// BuyingService ///
    /////////////////////
    @RequestMapping("/orderdataBuyingService")
    public List<BuyingOrderData> requestOrderDataBuyingService(HttpServletRequest request) throws SQLException  {
        String userid = SecurityConfig.getUserid(request);        
        return mypageService.getOrderDataBuyingService(userid);
    }
    
    @RequestMapping("/paymentDataBuyingService")
    public List<PaymentData> requestPaymentDataBuyingService(HttpServletRequest request) throws SQLException  {
        String userid = SecurityConfig.getUserid(request);
        return mypageService.getPaymentDataBuyingService(userid);
    }
    
    @RequestMapping("/updatePaymentProductBuyingService")
    public ResponseEntity<?> updatePaymentProductBuyingService(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException  {
        String userid = SecurityConfig.getUserid(request);
        return mypageService.updatePaymentProductBuyingService(data, userid);
    }
    
    @RequestMapping("/paymentDeliveryBuyingService")
    public List<PaymentData> requestPaymentDeliveryBuyingService(HttpServletRequest request) throws SQLException  {
        String userid = SecurityConfig.getUserid(request);
        return mypageService.getPaymentDeliveryBuyingService(userid);
    }
    
    @RequestMapping("/deliveryKoreaDataBuyingService")
    public List<DeliveryKoreaData> requestDeliveryKoreaDataBuyingService(HttpServletRequest request) throws SQLException  {
        String userid = SecurityConfig.getUserid(request);
        return mypageService.getDeliveryKoreaDataBuyingService(userid);
    }
}
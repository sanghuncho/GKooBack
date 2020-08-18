package com.gkoo.controller;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gkoo.data.PaymentHistoryData;
import com.gkoo.service.PaymentHistoryService;
import serviceBase.ServicePath;

@RestController
public class PaymentHistoryController {
    private final PaymentHistoryService paymentHistoryService;
    
    @Autowired
    public PaymentHistoryController(PaymentHistoryService paymentHistoryService) {
        this.paymentHistoryService = paymentHistoryService;
    }
    
    @CrossOrigin(origins = {ServicePath.PAYMENT_HISTORY_DEV, ServicePath.PAYMENT_HISTORY_PROD})
    @RequestMapping("/fetchPaymentHistoryTransfer/{userid}")
    public List<PaymentHistoryData> requestPaymentHistoryTransfer(HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        return paymentHistoryService.requestPaymentHistoryTransfer(userid);
    }
    
    @CrossOrigin(origins = {ServicePath.PAYMENT_HISTORY_DEV, ServicePath.PAYMENT_HISTORY_PROD})
    @RequestMapping("/fetchPaymentHistoryDeposit/{userid}")
    public List<PaymentHistoryData> requestPaymentHistoryDeposit(HttpServletRequest request, @PathVariable String userid) throws SQLException  {
        return paymentHistoryService.requestPaymentHistoryDeposit(userid);
    }
}

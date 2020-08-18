package com.gkoo.service;

import java.util.List;
import com.gkoo.data.PaymentHistoryData;

public interface PaymentHistoryService {
    List<PaymentHistoryData> requestPaymentHistoryTransfer(String userid);
    List<PaymentHistoryData> requestPaymentHistoryDeposit(String userid);
}

package com.gkoo.service;

import com.gkoo.data.PaymentHistoryData;

public interface PaymentHistoryService {
    PaymentHistoryData requestPaymentHistoryTransfer(String userid);
    PaymentHistoryData requestPaymentHistoryDeposit(String userid);
}

package com.gkoo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.gkoo.data.PaymentHistoryData;
import com.gkoo.db.PaymentHistoryDB;
import com.gkoo.service.PaymentHistoryService;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    @Override
    public List<PaymentHistoryData> requestPaymentHistoryTransfer(String userid) {
        return PaymentHistoryDB.getPaymentHistoryTransfer(userid);
    }

    @Override
    public List<PaymentHistoryData> requestPaymentHistoryDeposit(String userid) {
        return PaymentHistoryDB.getPaymentHistoryDeposit(userid);
    }

}

package com.gkoo.service;

import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.gkoo.data.EstimationService;

public interface BuyingService {
    public EstimationService fastEstimationBuyingService(@RequestBody HashMap<String, Object>[] data, String userid);
    public EstimationService estimationBuyingService(@RequestBody HashMap<String, Object>[] data, String userid);
    public ResponseEntity<?> createBuyingService(@RequestBody HashMap<String, Object>[] data, String userid);
}

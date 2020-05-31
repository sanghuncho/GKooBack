package com.gkoo.service;

import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.gkoo.data.EstimationService;
import com.gkoo.data.UserBaseInfo;

public interface BuyingService {
    //not used, otherwise gkooOpenApi serve it and it found out since 21.05.2020
    //public EstimationService fastEstimationBuyingService(@RequestBody HashMap<String, Object>[] data, String userid);
    public EstimationService estimationBuyingService(@RequestBody HashMap<String, Object>[] data, String userid);
    public ResponseEntity<?> createBuyingService(@RequestBody HashMap<String, Object>[] data, String userid);
    public UserBaseInfo getUserBaseInfo(String userid);
    public ResponseEntity<?> registerFavoriteAddress(@RequestBody HashMap<String, Object>[] data, String userid);
}

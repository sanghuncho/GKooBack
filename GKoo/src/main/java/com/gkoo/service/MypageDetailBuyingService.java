package com.gkoo.service;

import java.io.IOException;
import java.sql.SQLException;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface MypageDetailBuyingService {
    public ResponseEntity<?> deleteBuyingServiceData(String orderid) throws JsonParseException, JsonMappingException, IOException, SQLException;
}

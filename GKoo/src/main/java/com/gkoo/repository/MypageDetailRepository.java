package com.gkoo.repository;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.PersonalData;
import mypage.information.ProductsCommonInformation;
import mypage.information.ProductsInformation.Product;
import mypage.information.RecipientData;

/**
 *
 * @author sanghuncho
 *
 * @since  18.11.2019
 *
 */
public interface MypageDetailRepository {
    public PersonalData getOrderingpersonInfo(String fullname);
    
    public RecipientData getRecipientInfo(String username, String number);
    
    public ProductsCommonInformation getProductsCommonInfo(String username, String number);
    
    public List<Product> getProductsInfo(String username, String number);

    public ResponseEntity<?> updateRecipientData(String memberId, HashMap<String, Object>[] data);
    
    public ResponseEntity<?> updateDataEditorProductsList(String memberId, HashMap<String, Object>[] data);
    
    public ResponseEntity<?> deleteShipingServiceData(String memberId, HashMap<String, Object>[] data);
}

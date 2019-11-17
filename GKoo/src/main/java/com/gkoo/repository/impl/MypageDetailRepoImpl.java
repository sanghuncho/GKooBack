package com.gkoo.repository.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.PersonalData;
import com.gkoo.db.MypageDetailDB;
import com.gkoo.repository.MypageDetailRepository;
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
public class MypageDetailRepoImpl implements MypageDetailRepository {

    @Override
    public PersonalData getOrderingpersonInfo(String fullname) {
        PersonalData personaldata = new PersonalData();
        personaldata.setFullname(fullname);
        personaldata.setShipServiceCenter("독일");
        return personaldata;
    }

    @Override
    public RecipientData getRecipientInfo(String username, String number) {
        return MypageDetailDB.getRecipientInfo(username, number);
    }

    @Override
    public List<Product> getProductsInfo(String username, String number) {
        return MypageDetailDB.getProductsInfo(username, number);
    }

    @Override
    public ProductsCommonInformation getProductsCommonInfo(String username, String number) {
        return MypageDetailDB.getProductsCommonInfo(username, number);
    }

    @Override
    public ResponseEntity<?> updateRecipientData(String memberId, HashMap<String, Object>[] data) {
        return MypageDetailDB.updateRecipientData(memberId, data);
    }

    @Override
    public ResponseEntity<?> updateDataEditorProductsList(String memberId,
            HashMap<String, Object>[] data) {
        return MypageDetailDB.updateDataEditorProductsList(memberId, data);
    }

    @Override
    public ResponseEntity<?> deleteShipingServiceData(String memberId,
            HashMap<String, Object>[] data) {
        return MypageDetailDB.deleteShipingServiceData(memberId, data);
    }

}

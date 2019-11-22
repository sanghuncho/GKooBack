package com.gkoo.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.gkoo.data.PersonalData;
import com.gkoo.repository.MypageDetailRepository;
import com.gkoo.service.MypageDetailService;
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
@Service
public class MypageDetailServiceImpl implements MypageDetailService {

    private final MypageDetailRepository mypageDetailRepository;
    
    @Autowired
    public MypageDetailServiceImpl(MypageDetailRepository mypageDetailRepository) {
        this.mypageDetailRepository = mypageDetailRepository;
    }

    @Override
    public PersonalData getOrderingpersonInfo(String fullname) {
        return mypageDetailRepository.getOrderingpersonInfo(fullname);
    }

    @Override
    public RecipientData getRecipientInfo(String username, String number) {
        return mypageDetailRepository.getRecipientInfo(username, number);
    }

    @Override
    public ProductsCommonInformation getProductsCommonInfo(String username, String number) {
        return mypageDetailRepository.getProductsCommonInfo(username, number);
    }

    @Override
    public List<Product> getProductsInfo(String username, String number) {
        return mypageDetailRepository.getProductsInfo(username, number);
    }

    @Override
    public ResponseEntity<?> updateRecipientData(String memberId, HashMap<String, Object>[] data) {
        return mypageDetailRepository.updateRecipientData(memberId, data);
    }

    @Override
    public ResponseEntity<?> updateDataEditorProductsList(String memberId,
            HashMap<String, Object>[] data) {
        return mypageDetailRepository.updateDataEditorProductsList(memberId, data);
    }

    @Override
    public ResponseEntity<?> deleteShipingServiceData(String memberId,
            HashMap<String, Object>[] data) {
        return mypageDetailRepository.deleteShipingServiceData(memberId, data);
    }
}

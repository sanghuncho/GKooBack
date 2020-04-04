package com.gkoo.service.impl;

import org.springframework.stereotype.Service;
import com.gkoo.db.MypageDB;
import com.gkoo.service.MypageShippingAddressService;

@Service
public class MypageShippingAddressServiceImpl implements MypageShippingAddressService {

    @Override
    public String getPersonalBoxAddress(String userid) {
        return MypageDB.getPersonalBoxAddress(userid);
    }

}

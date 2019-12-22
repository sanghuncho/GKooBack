package com.gkoo.repository.impl;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.db.AddressManagerDB;
import com.gkoo.repository.AddressManagerRepository;

/**
 *
 * @author sanghuncho
 *
 * @since  19.12.2019
 *
 */
@Repository
public class AddressManagerRepoImpl implements AddressManagerRepository {

    @Override
    public List<FavoriteAddress> getFavoriteAddressList(String userid) {
        return AddressManagerDB.getFavoriteAddressList(userid);
    }

    @Override
    public ResponseEntity<?> createFavoriteAddress(FavoriteAddress favoriteAddress, String userid) {
        return AddressManagerDB.createFavoriteAddress(favoriteAddress, userid);
    }

    @Override
    public ResponseEntity<?> updateFavoriteAddress(FavoriteAddress favoriteAddress, String userid) {
        return AddressManagerDB.updateFavoriteAddress(favoriteAddress, userid);
    }

    @Override
    public ResponseEntity<?> deleteFavoriteAddress(int favoriteAddress_id, String userid) {
        return AddressManagerDB.deleteFavoriteAddress(favoriteAddress_id, userid);
    }
}
package com.gkoo.repository;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.gkoo.data.FavoriteAddress;

/**
 *
 * @author sanghuncho
 *
 * @since  19.12.2019
 *
 */
@Repository
public interface AddressManagerRepository {
    public List<FavoriteAddress> getFavoriteAddressList(String userid);
    public ResponseEntity<?> createFavoriteAddress(FavoriteAddress favoriteAddress, String userid);
    public ResponseEntity<?> updateFavoriteAddress(FavoriteAddress favoriteAddress, String userid);
    public ResponseEntity<?> deleteFavoriteAddress(int favoriteAddress_id, String userid);
}

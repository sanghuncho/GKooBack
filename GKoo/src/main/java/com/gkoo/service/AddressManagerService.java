package com.gkoo.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.FavoriteAddress;

public interface AddressManagerService {
    public List<FavoriteAddress> getFavoriteAddressList(String userid);
    public ResponseEntity<?> createFavoriteAddress(HashMap<String, Object>[] data, String userid);
    public ResponseEntity<?> updateFavoriteAddress(HashMap<String, Object>[] data, String userid);
    public ResponseEntity<?> deleteFavoriteAddress(HashMap<String, Object>[] data, String userid);
}

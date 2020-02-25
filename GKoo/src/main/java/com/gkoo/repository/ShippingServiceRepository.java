package com.gkoo.repository;

import org.springframework.http.ResponseEntity;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.data.UserBaseInfo;
import shippingService.ShippingServiceModel;

/**
 * @author sanghuncho
 *
 */
public interface ShippingServiceRepository {
    public ResponseEntity<?> createShippingService(ShippingServiceModel shippingModel);
    public UserBaseInfo getUserBaseInfo(String userid);
    public ResponseEntity<?> registerFavoriteAddress(FavoriteAddress favoriteAddress, String userid);
    public ResponseEntity<?> updateDataShippingService(ShippingServiceModel model);
    public ResponseEntity<?> deleteShipingServiceData(String userid, String orderNumber);
}

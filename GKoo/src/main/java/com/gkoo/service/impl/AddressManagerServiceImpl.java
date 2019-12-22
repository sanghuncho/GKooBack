package com.gkoo.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.exception.CustomerStatusException;
import com.gkoo.repository.AddressManagerRepository;
import com.gkoo.service.AddressManagerService;

@Service
public class AddressManagerServiceImpl implements AddressManagerService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final AddressManagerRepository addressManagerRepository;
    
    @Autowired
    public AddressManagerServiceImpl(AddressManagerRepository addressManagerRepository) {
        this.addressManagerRepository = addressManagerRepository;
    }

    @Override
    public List<FavoriteAddress> getFavoriteAddressList(String userid) {
        return addressManagerRepository.getFavoriteAddressList(userid);
    }

    @Override
    public ResponseEntity<?> createFavoriteAddress(HashMap<String, Object>[] data, String userid) {
        ObjectMapper mapper = new ObjectMapper();
        FavoriteAddress favoriteAddress = null;
        try {
            favoriteAddress = mapper.readValue(data[0].get("favoriteAddressData").toString(), FavoriteAddress.class);
            favoriteAddress.setUserid(userid);
        } catch (IOException ex) {
            String error = "Error mapping creating favoriteAddress";
            LOGGER.error(error, ex);
            throw new CustomerStatusException(error, ex);
        }
        return addressManagerRepository.createFavoriteAddress(favoriteAddress, userid);
    }

    @Override
    public ResponseEntity<?> updateFavoriteAddress(HashMap<String, Object>[] data, String userid) {
        ObjectMapper mapper = new ObjectMapper();
        FavoriteAddress favoriteAddress = null;
        try {
            favoriteAddress = mapper.readValue(data[0].get("favoriteAddress").toString(), FavoriteAddress.class);
        } catch (IOException ex) {
            String error = "Error mapping for updating favoriteAddress";
            LOGGER.error(error, ex);
            throw new CustomerStatusException(error, ex);
        }
        return addressManagerRepository.updateFavoriteAddress(favoriteAddress, userid);
    }

    @Override
    public ResponseEntity<?> deleteFavoriteAddress(HashMap<String, Object>[] data, String userid) {
        String deletedAddressIdData= data[0].get("favoriteAddressId").toString();
        int deletedAddressId = Integer.parseInt(deletedAddressIdData);
        return addressManagerRepository.deleteFavoriteAddress(deletedAddressId, userid);
    }
}
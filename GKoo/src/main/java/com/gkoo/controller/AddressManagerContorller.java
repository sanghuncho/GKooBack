package com.gkoo.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gkoo.configuration.SecurityConfig;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.service.AddressManagerService;
import serviceBase.ServicePath;

/**
 *
 * @author sanghuncho
 *
 * @since  19.12.2019
 *
 */
@RestController
public class AddressManagerContorller {
    private static final Logger LOGGER = LogManager.getLogger();
    private final AddressManagerService addressManagerService;
    
    @Autowired
    public AddressManagerContorller(AddressManagerService addressManagerService) {
        this.addressManagerService = addressManagerService;
    }

    @CrossOrigin(origins = ServicePath.FAVORITE_ADDRESS_MANAGER)
    @RequestMapping("/getFavoriteAddressList")
    public List<FavoriteAddress> getFavoriteAddressList(HttpServletRequest request){
        String userid = SecurityConfig.getUserid(request);
        return addressManagerService.getFavoriteAddressList(userid);
    }
}

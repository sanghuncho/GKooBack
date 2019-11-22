package com.gkoo.repository.impl;

import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Repository;
import com.gkoo.data.CustomerStatus;
import com.gkoo.db.CustomerStatusDB;
import com.gkoo.repository.CustomerStatusRepository;

@Repository
public class CustomerStatusRepoImpl implements CustomerStatusRepository {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void checkUserid(AccessToken accessToken) {
        String userid = accessToken.getPreferredUsername();
        String lastname = accessToken.getFamilyName();
        String firstname = accessToken.getGivenName();
        Boolean existUserid = null;
        try {
            existUserid = CustomerStatusDB.existUserid(userid);
        } catch (SQLException e) {
            LOGGER.error("CustomerStatusRepoImpl-checkUserid:" + userid, e);
        }
        if (!existUserid) {
            CustomerStatusDB.registerInitialCustomer(userid, lastname, firstname);
        }
    }

    @Override
    public CustomerStatus getCustomerStatus(String userid) {
        return CustomerStatusDB.getCustomerStatus(userid);
    }
}

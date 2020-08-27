package com.gkoo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.AuctionBidData;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.exception.MypageException;
import databaseUtil.ConnectionDB;

public class AuctionServiceDB {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static final String CREATE_AUCTION_BID = "INSERT INTO favorite_address (userid, name_kor, name_eng, transit_nr, phonenumber_first, phonenumber_second, zip_code, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    public static ResponseEntity<?> requestAuctionBidService(AuctionBidData bidData, String userid){
        ConnectionDB.connectSQL();
        ResultSet resultSet = null;
        
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(FETCH_FAVORITE_ADDRESS_LIST);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            favoriteAddressList = writeFavoriteAddressList(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching favoriteAddressList";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        return favoriteAddressList;
    }
}

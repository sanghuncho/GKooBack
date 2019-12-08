package com.gkoo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.exception.MypageException;
import databaseUtil.ConnectionDB;

public class AddressManagerDB {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static final String UPDATE_FAVORITE_ADDRESS = "UPDATE favorite_address SET name_kor=?, name_eng=?, transit_nr=?, phone_prefic=?, phone_interfix=?, phone_suffix=?, phone_second=?, zip_code=?, address=?, detail_address=? where id=?";
    private static final String FETCH_FAVORITE_ADDRESS_LIST = "SELECT * FROM FAVORITE_ADDRESS WHERE userid=?";
    private static final String CREATE_FAVORITE_ADDRESS = "INSERT INTO favorite_address (userid, name_kor, name_eng, transit_nr, phone_prefic, phone_interfix, phone_suffix, phone_second, zip_code, address, detail_address ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_FAVORITE_ADDRESS = "DELETE FROM favorite_address where id=?, userid=?";
    
   public static List<FavoriteAddress> getFavoriteAddressList(String userid){
        ConnectionDB.connectSQL();
        List<FavoriteAddress> favoriteAddressList = new ArrayList<>();
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
    
    private static List<FavoriteAddress> writeFavoriteAddressList(ResultSet rs) throws SQLException {
        List<FavoriteAddress> favoriteAddressList = new ArrayList<>();
        while (rs.next()) {
            FavoriteAddress favoriteAddress = new FavoriteAddress();
            favoriteAddress.setNameKor(rs.getString("name_kor"));
            favoriteAddress.setNameEng(rs.getString("name_eng"));
            favoriteAddress.setTransitNr(rs.getString("transit_nr"));
            favoriteAddress.setPhonePrefic(rs.getString("phone_prefic"));
            favoriteAddress.setPhoneInterfix(rs.getString("phone_interfix"));
            favoriteAddress.setPhoneSuffix(rs.getString("phone_suffix"));
            favoriteAddress.setPhoneSuffix(rs.getString("phone_second"));
            favoriteAddress.setZipCode(rs.getString("zip_code"));
            favoriteAddress.setAddress(rs.getString("address"));
            favoriteAddress.setAddressDetails(rs.getString("detail_address"));
            favoriteAddressList.add(favoriteAddress);
        }
        return favoriteAddressList;
    }
    
    public static ResponseEntity<?> createFavoriteAddress(FavoriteAddress favoriteAddress, String userid){
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(CREATE_FAVORITE_ADDRESS);){
            psmt.setString(1, userid);
            psmt.setString(2, favoriteAddress.getNameKor());
            psmt.setString(3, favoriteAddress.getNameEng());
            psmt.setString(4, favoriteAddress.getTransitNr());
            psmt.setString(5, favoriteAddress.getPhonePrefic());
            psmt.setString(6, favoriteAddress.getPhoneInterfix());
            psmt.setString(7, favoriteAddress.getPhoneSuffix());
            psmt.setString(8, favoriteAddress.getPhoneSecond());
            psmt.setString(9, favoriteAddress.getZipCode());
            psmt.setString(10, favoriteAddress.getAddress());
            psmt.setString(11, favoriteAddress.getAddressDetails());
        } catch (SQLException e) {
            String error = "Error creating favorite address";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }

    public static ResponseEntity<?> updateFavoriteAddress(FavoriteAddress favoriteAddress, String userid){
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(UPDATE_FAVORITE_ADDRESS);){
            psmt.setString(1, favoriteAddress.getNameKor());
            psmt.setString(2, favoriteAddress.getNameEng());
            psmt.setString(3, favoriteAddress.getTransitNr());
            psmt.setString(4, favoriteAddress.getPhonePrefic());
            psmt.setString(5, favoriteAddress.getPhoneInterfix());
            psmt.setString(6, favoriteAddress.getPhoneSuffix());
            psmt.setString(7, favoriteAddress.getPhoneSecond());
            psmt.setString(8, favoriteAddress.getZipCode());
            psmt.setString(9, favoriteAddress.getAddress());
            psmt.setString(10, favoriteAddress.getAddressDetails());
            psmt.setString(11, userid);
            psmt.executeUpdate();
        } catch (SQLException e) {
            String error = "Error updating favorite address";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }

    public static ResponseEntity<?> deleteFavoriteAddress(int favoriteAddress_id, String userid){
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(DELETE_FAVORITE_ADDRESS);){
            psmt.setInt(1, favoriteAddress_id);
            psmt.setString(2, userid);
            psmt.executeUpdate();
        } catch (SQLException e) {
            String error = "Error updating favorite address";
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
}
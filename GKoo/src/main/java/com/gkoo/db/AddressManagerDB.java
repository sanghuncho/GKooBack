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
import util.LogMessenger;

public class AddressManagerDB {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static final String UPDATE_FAVORITE_ADDRESS = "UPDATE favorite_address SET name_kor=?, name_eng=?, transit_nr=?, phonenumber_first=?, phonenumber_second=?, zip_code=?, address=? where id=?";
    private static final String FETCH_FAVORITE_ADDRESS_LIST = "SELECT * FROM FAVORITE_ADDRESS WHERE userid=?";
    private static final String CREATE_FAVORITE_ADDRESS = "INSERT INTO favorite_address (userid, name_kor, name_eng, transit_nr, phonenumber_first, phonenumber_second, zip_code, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
    private static final String DELETE_FAVORITE_ADDRESS = "DELETE FROM favorite_address where id=? and userid=?";
    
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
            favoriteAddress.setId(rs.getInt("id"));
            favoriteAddress.setUserid(rs.getString("userid"));
            favoriteAddress.setNameKor(rs.getString("name_kor"));
            favoriteAddress.setNameEng(rs.getString("name_eng"));
            favoriteAddress.setTransitNr(rs.getString("transit_nr"));
            favoriteAddress.setPhonenumberFirst(rs.getString("phonenumber_first"));
            favoriteAddress.setPhonenumberSecond(rs.getString("phonenumber_second"));
            favoriteAddress.setZipCode(rs.getString("zip_code"));
            favoriteAddress.setAddress(rs.getString("address"));
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
            psmt.setString(5, favoriteAddress.getPhonenumberFirst());
            psmt.setString(6, favoriteAddress.getPhonenumberSecond());
            psmt.setString(7, favoriteAddress.getZipCode());
            psmt.setString(8, favoriteAddress.getAddress());
            psmt.executeUpdate();
        } catch (SQLException ex) {
            String error_message = LogMessenger.getMessage("Creating favorite address is failed", userid);
            LOGGER.error(error_message, ex);
            throw new MypageException(error_message, ex);
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
            psmt.setString(4, favoriteAddress.getPhonenumberFirst());
            psmt.setString(5, favoriteAddress.getPhonenumberSecond());
            psmt.setString(6, favoriteAddress.getZipCode());
            psmt.setString(7, favoriteAddress.getAddress());
            psmt.setInt(8, favoriteAddress.getId());
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
            String error_message = LogMessenger.getMessage("Deleting of favorite address is failed", "favorite_address_id", 
                    Integer.toString(favoriteAddress_id), "userid", userid); 
            LOGGER.error(error_message, e);
            throw new MypageException(error_message, e);
        }

        String message = LogMessenger.getMessage("favorite address is deleted", "favorite_address_id", 
                Integer.toString(favoriteAddress_id), "userid", userid); 
        LOGGER.info(message);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
}
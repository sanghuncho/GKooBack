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
import com.gkoo.data.AuctionBidData;
import com.gkoo.enums.AuctionResult;
import com.gkoo.exception.AuctionServiceException;
import databaseUtil.ConnectionDB;
import util.DateUtil;

public class AuctionServiceDB {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static final String CREATE_AUCTION_BID = 
            "INSERT INTO auction_bid (userid, product_url, auction_bid_date, bid_value, auction_message, auction_result) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FETCH_AUCTION_BID_LIST = "SELECT * FROM AUCTION_BID WHERE USERID = ?"; 
    private static final String DELETE_AUCTION_BID_SERVICE = "UPDATE auction_bid SET deleted = true where object_id = ? and userid = ?";
    
    public static ResponseEntity<?> requestAuctionBidService(AuctionBidData bidData){
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(CREATE_AUCTION_BID);){
            psmt.setString(1, bidData.getUserid());
            psmt.setString(2, bidData.getProductUrl());
            psmt.setDate(3, bidData.getAuctionBidDate());
            psmt.setDouble(4, bidData.getBidValue());
            psmt.setString(5, bidData.getAuctionMessage());
            psmt.setInt(6, AuctionResult.BID_READY.getCode());
            psmt.executeUpdate();
        } catch (SQLException e) {
            String error = "Error creating auctionBidService";
            LOGGER.error(error, e);
            throw new AuctionServiceException(error, e);
        }
        
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    public static List<AuctionBidData> requestAuctionBidDataList(String userid){
        ConnectionDB.connectSQL();
        ResultSet resultSet = null;
        List<AuctionBidData> auctionBidDataList = null;
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(FETCH_AUCTION_BID_LIST);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            auctionBidDataList = writeAuctionBidDataList(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching auctionBidDataList";
            LOGGER.error(error, e);
        }
        return auctionBidDataList;
    }
    
    private static List<AuctionBidData> writeAuctionBidDataList(ResultSet rs){
        List<AuctionBidData> auctionBidDataList = new ArrayList<>();
        try {
            while (rs.next()) {
                AuctionBidData auctionBidData = new AuctionBidData();
                Boolean deleted = rs.getBoolean("deleted");
                try {
                    if (deleted == null || deleted == false) {
                        auctionBidData.setObjectid(rs.getInt("object_id"));
                        auctionBidData.setProductUrl(rs.getString("product_url"));
                        auctionBidData.setBidValue(rs.getDouble("bid_value"));
                        auctionBidData.setAuctionBidDate(DateUtil.toLocalDate(rs.getDate("auction_bid_date")));
                        auctionBidData.setAuctionMessage(rs.getString("auction_message"));
                        auctionBidData.setAuctionResult(AuctionResult.getAuctionResult(rs.getInt("auction_result")));
                    }
                } catch (SQLException e) {
                    String error = "Error fetching auctionData";
                    LOGGER.error(error, e);
                }
                auctionBidDataList.add(auctionBidData);
            }
        } catch (SQLException e) {
            String error = "Error fetching paymentData";
            LOGGER.error(error, e);
        }
        return auctionBidDataList;
    }
    
    public static ResponseEntity<?> deleteAuctionBidService(AuctionBidData bidData){
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(DELETE_AUCTION_BID_SERVICE);){
            psmt.setInt(1, bidData.getObjectid());
            psmt.setString(2, bidData.getUserid());
            psmt.executeUpdate();
        } catch (SQLException e) {
            String error = "Error deleting auctionBidService";
            LOGGER.error(error, e);
            throw new AuctionServiceException(error, e);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
}

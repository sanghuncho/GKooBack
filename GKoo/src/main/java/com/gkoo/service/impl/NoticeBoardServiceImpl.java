package com.gkoo.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.data.NoticeData;
import com.gkoo.data.UserBaseInfo;
import com.gkoo.exception.CustomerStatusException;
import com.gkoo.repository.CustomerStatusRepository;
import com.gkoo.service.NoticeBoardService;
import databaseUtil.ConnectionDB;

/**
 *
 * @author sanghuncho
 *
 * @since  09.01.2020
 *
 */
@Service
public class NoticeBoardServiceImpl implements NoticeBoardService  {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String FETCH_NOTICES = "select * from notices";
    
    @Override
    public List<NoticeData> getNotices() {
        ConnectionDB.connectSQL();
        List<NoticeData> notices = new ArrayList<>();
        ResultSet resultSet = null;
        
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(FETCH_NOTICES);){
            resultSet = psmt.executeQuery();
            notices = writeNotices(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching notices";
            LOGGER.error(error, e);
            throw new CustomerStatusException(error, e);
        }
        return notices;
    }
    
    private static List<NoticeData> writeNotices(ResultSet rs) throws SQLException {
        List<NoticeData> notices = new ArrayList<>();
        while (rs.next()) {
            NoticeData notice = new NoticeData();
            notice.setNoticeid(rs.getInt("id"));
            notice.setNoticeTitle(rs.getString("noticeTitle"));
            notice.setNoticeContent(rs.getString("noticeContent"));
            notice.setNoticeDate(rs.getDate("noticeDate"));
            notices.add(notice);
        }
        return notices;
    }
}

package motherj.noticeModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataBase.ConnectionDB;

public class NoticeDAO {

	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Connection conn;

	public NoticeDAO() {}
	
	public List<Notice> getNoticeList() {
		
		ConnectionDB.connectSQL();
		Connection conn = ConnectionDB.getConnectInstance();
		List<Notice> noticeList = null;
		try {
			this.statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		 try {
			resultSet = this.statement
			         .executeQuery("select * from notice");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		try {
			 noticeList = writeResultNoticeListSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return noticeList;
	}
	
	public Notice getNoticeContent(String nr) {
		ConnectionDB.connectSQL();
		Connection conn = ConnectionDB.getConnectInstance();
		Notice notice = null;
		try {
			this.statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		 try {
			resultSet = this.statement
			         .executeQuery("select * from notice where notice_nr =" + nr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 try {
			 notice = writeResultNoticeContentSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return notice;
	}
	
	private Notice writeResultNoticeContentSet(ResultSet rs) throws SQLException {
		Notice notice = new Notice();
		while (rs.next()) {
        	notice.setNoticeNumber(rs.getInt("notice_nr"));
        	//int number = rs.getInt("notice_nr");
        	notice.setNoticeTitle(rs.getString("notice_title"));
        	//String title = rs.getString("notice_title");
        	notice.setNoticeContent(rs.getString("notice_content"));
        	//String content = rs.getString("notice_content");
        	notice.setNoticeWriter(rs.getString("notice_writer"));
        	//String writer = rs.getString("notice_writer");
        	//notice.setNoticeDate(rs.getDate("notice_registration"));
        	Date date = rs.getDate("notice_registration");
        	LocalDate localDate = 
        			LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(date) );
        	notice.setNoticeDate(localDate);
        	notice.setNoticeHit(rs.getInt("notice_hits"));
		}
        return notice;
    }
	
	private List<Notice> writeResultNoticeListSet(ResultSet rs) throws SQLException {
        // ResultSet is initially before the first data set
		List<Notice> noticeList =  new ArrayList<Notice>();
        while (rs.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
        	Notice notice = new Notice();
        	notice.setNoticeNumber(rs.getInt("notice_nr"));
        	//int number = rs.getInt("notice_nr");
        	notice.setNoticeTitle(rs.getString("notice_title"));
        	//String title = rs.getString("notice_title");
        	notice.setNoticeContent(rs.getString("notice_content"));
        	//String content = rs.getString("notice_content");
        	notice.setNoticeWriter(rs.getString("notice_writer"));
        	//String writer = rs.getString("notice_writer");
        	//notice.setNoticeDate(rs.getDate("notice_registration"));
        	Date date = rs.getDate("notice_registration");
        	LocalDate localDate = 
        			LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(date) );
        	notice.setNoticeDate(localDate);
        	notice.setNoticeHit(rs.getInt("notice_hits"));
        	//int hits = rs.getInt("notice_hits");
        	noticeList.add(notice);
        	/*System.out.println("number: " + number);
            System.out.println("title: " + title);
            System.out.println("content: " + content);
            System.out.println("writer: " + writer);*/
        }
        return noticeList;
    }

	
	private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
    }
}

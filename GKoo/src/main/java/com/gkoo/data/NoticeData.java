package com.gkoo.data;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author sanghuncho
 *
 * @since  10.01.2020
 *
 */
public class NoticeData {
    
    private int noticeid;
    private String noticeTitle;
    private String noticeContent;
    private Date noticeDate;
    
    public NoticeData() {}
    
    public int getNoticeid() {
        return noticeid;
    }
    
    public void setNoticeid(int noticeid) {
        this.noticeid = noticeid;
    }
    
    public String getNoticeTitle() {
        return noticeTitle;
    }
    
    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }
    
    public String getNoticeContent() {
        return noticeContent;
    }
    
    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }
    
    public Date getNoticeDate() {
        return noticeDate;
    }
    
    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }
}

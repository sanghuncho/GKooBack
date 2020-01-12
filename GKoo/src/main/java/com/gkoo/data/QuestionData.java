package com.gkoo.data;

import java.sql.Date;
import java.time.LocalDate;

public class QuestionData {
    private int qnaid;
    private String questionTitle;
    private String questionContent;
    private LocalDate questionDate;
    private String userid;
    
    public QuestionData() {}
    
    public QuestionData(String questionTitle, String questionContent, LocalDate questionDate) {
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.questionDate = questionDate;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Date getQuestionDate() {
      return Date.valueOf(questionDate);
    }
    
    public void setQuestionDate(LocalDate questionDate) {
        this.questionDate = questionDate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getQnaid() {
        return qnaid;
    }

    public void setQnaid(int qnaid) {
        this.qnaid = qnaid;
    }
}

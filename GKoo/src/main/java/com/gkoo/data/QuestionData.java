package com.gkoo.data;

import java.time.LocalDate;
import java.util.Date;

public class QuestionData {
    private String questionTitle;
    private String questionContent;
    private Date questionDate;
    
    public QuestionData() {}
    
    public QuestionData(String questionTitle, String questionContent, Date questionDate) {
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

    public java.sql.Date getQuestionDate() {
        return new java.sql.Date(questionDate.getTime());
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }
}

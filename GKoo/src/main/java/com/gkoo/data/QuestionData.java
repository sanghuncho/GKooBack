package com.gkoo.data;

import java.sql.Date;
import java.time.LocalDate;

public class QuestionData {
    private String questionTitle;
    private String questionContent;
    private LocalDate questionDate;
    
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
}

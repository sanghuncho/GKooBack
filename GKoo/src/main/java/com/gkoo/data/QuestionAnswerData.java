package com.gkoo.data;

import java.util.Date;
import com.gkoo.enums.AnswerState;

/**
 *
 * @author sanghuncho
 *
 * @since  10.01.2020
 *
 */
public class QuestionAnswerData extends QuestionData {
    private String answerContent;
    private Date answerDate;
    private AnswerState answerState;
    
    public QuestionAnswerData() {}

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public AnswerState getAnswerState() {
        return answerState;
    }

    public void setAnswerState(AnswerState answerState) {
        this.answerState = answerState;
    }
}

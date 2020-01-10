package com.gkoo.service.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkoo.data.FavoriteAddress;
import com.gkoo.data.QuestionAnswerData;
import com.gkoo.data.QuestionData;
import com.gkoo.enums.AnswerState;
import com.gkoo.exception.CustomerStatusException;
import com.gkoo.exception.MypageException;
import com.gkoo.service.QuestionBoardService;
import databaseUtil.ConnectionDB;

/**
 *
 * @author sanghuncho
 *
 * @since  10.01.2020
 *
 */
@Service
public class QuestionBoardServiceImpl implements QuestionBoardService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String CREATE_QUESTION_DATA = "INSERT INTO QEUSTION_ANSWER (question_title, question_content, question_date) VALUES (?, ?, ?)";
    private static final String FETCH_QUESTION_ANSWER_LIST = "SELECT * FROM QEUSTION_ANSWER WHERE userid=?";
    
    @Override
    public ResponseEntity<?> createQuestion(HashMap<String, Object>[] data, String userid){
        ObjectMapper mapper = new ObjectMapper();
        QuestionData questionData = null;
        try {
            questionData = mapper.readValue(data[0].get("questionData").toString(), QuestionData.class);
        } catch (IOException ex) {
            String error = "Error mapping questionData: "+data+"/ userid: "+userid;
            LOGGER.error(error, ex);
            throw new CustomerStatusException(error, ex);
        }
        return writeQuestionData(questionData, userid);
    }
    
    public static ResponseEntity<?> writeQuestionData(QuestionData questionData, String userid){
        ConnectionDB.connectSQL();
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(CREATE_QUESTION_DATA);){
            psmt.setString(1, questionData.getQuestionTitle());
            psmt.setString(2, questionData.getQuestionTitle());
            psmt.setDate(3, questionData.getQuestionDate());
            psmt.setString(4, AnswerState.READY_TO_ANSWER.toString());
            psmt.executeUpdate();
        } catch (SQLException e) {
            String error = "Error creating questionData: " + questionData+"/ userid: "+userid;
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }

    @Override
    public List<QuestionAnswerData> getQuestionAnswerList(String userid){
        ConnectionDB.connectSQL();
        List<QuestionAnswerData> questionAnswerList = new ArrayList<>();
        ResultSet resultSet = null;
        
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(FETCH_QUESTION_ANSWER_LIST);){
            psmt.setString(1, userid);
            resultSet = psmt.executeQuery();
            questionAnswerList = writeQuestionAnswerList(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching questionAnswerList from DB:" + userid;
            LOGGER.error(error, e);
            throw new MypageException(error, e);
        }
        return questionAnswerList;
    }
    
    private static List<QuestionAnswerData> writeQuestionAnswerList(ResultSet rs) throws SQLException {
        List<QuestionAnswerData> questionAnswerList = new ArrayList<>();
        while (rs.next()) {
            QuestionAnswerData questionAnswerData = new QuestionAnswerData();
            questionAnswerData.setQuestionTitle(rs.getString("question_title"));
            questionAnswerData.setQuestionContent(rs.getString("question_content"));
            questionAnswerData.setQuestionDate(rs.getDate("question_date"));
            questionAnswerData.setAnswerContent(rs.getString("answer_content"));
            questionAnswerData.setAnswerState(AnswerState.valueOf(rs.getString("answer_state")));
            questionAnswerList.add(questionAnswerData);
        }
        return questionAnswerList;
    }
}

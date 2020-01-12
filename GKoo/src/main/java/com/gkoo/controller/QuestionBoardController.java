package com.gkoo.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gkoo.configuration.SecurityConfig;
import com.gkoo.data.QuestionAnswerData;
import com.gkoo.service.QuestionBoardService;
import serviceBase.ServicePath;

/**
 *
 * @author sanghuncho
 *
 * @since  09.01.2020
 *
 */
@RestController
public class QuestionBoardController {
    private static final Logger LOGGER = LogManager.getLogger();
    private final QuestionBoardService questionBoardService;
    
    @Autowired
    public QuestionBoardController(QuestionBoardService questionBoardService){
        this.questionBoardService = questionBoardService;
    }
    
    @CrossOrigin(origins = ServicePath.QUESTION_BOARD)
    @RequestMapping(value = "/registerQuestion", method = RequestMethod.POST)
    public ResponseEntity<?> createQuestion(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) throws SQLException {
        String userid = SecurityConfig.getUserid(request);
        return questionBoardService.createQuestion(data, userid);
    }
    
    @CrossOrigin(origins = ServicePath.QUESTION_BOARD)
    @RequestMapping("/getQuestionAnswerList")
    public List<QuestionAnswerData> getQuestionAnswerList(HttpServletRequest request) throws SQLException {
        String userid = SecurityConfig.getUserid(request);
        return questionBoardService.getQuestionAnswerList(userid);
    }
}

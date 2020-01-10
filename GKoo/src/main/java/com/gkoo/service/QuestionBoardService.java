package com.gkoo.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.gkoo.data.QuestionAnswerData;

public interface QuestionBoardService {
    public List<QuestionAnswerData> getQuestionAnswerList(String userid);
    public ResponseEntity<?> createQuestion(HashMap<String, Object>[] data, String userid);
}

package com.elearning.service.testPaper;

import com.elearning.pojo.testPaper.TpaPaperQuestions;

import java.util.List;

public interface ITpaPaperQuestionsService {

    TpaPaperQuestions selectByPrimaryKey(Integer ID);

    List<TpaPaperQuestions> findByPaperIdOrderBySequence(Integer paperId);

}

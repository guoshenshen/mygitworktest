package com.elearning.dao.testPaper;

import com.elearning.pojo.testPaper.TpaPaperQuestions;

import java.util.List;

public interface TpaPaperQuestionsMapper {

    int deleteByPrimaryKey(Integer ID);

    int insert(TpaPaperQuestions record);

    int insertSelective(TpaPaperQuestions record);

    TpaPaperQuestions selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TpaPaperQuestions record);

    int updateByPrimaryKey(TpaPaperQuestions record);

    List<TpaPaperQuestions> findByPaperIdOrderBySequence(Integer paperId);

}
package com.elearning.dao.testPaper;

import com.elearning.pojo.testPaper.TpaQuestions;

public interface TpaQuestionsMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TpaQuestions record);

    int insertSelective(TpaQuestions record);

    TpaQuestions selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TpaQuestions record);

    int updateByPrimaryKey(TpaQuestions record);
}
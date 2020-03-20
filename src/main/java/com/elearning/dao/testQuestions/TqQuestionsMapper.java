package com.elearning.dao.testQuestions;

import com.elearning.pojo.testQuestions.TqQuestions;

public interface TqQuestionsMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TqQuestions record);

    int insertSelective(TqQuestions record);

    TqQuestions selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TqQuestions record);

    int updateByPrimaryKey(TqQuestions record);
}
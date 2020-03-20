package com.elearning.dao.testQuestions;

import com.elearning.pojo.testQuestions.TqQuestionItem;

public interface TqQuestionItemMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TqQuestionItem record);

    int insertSelective(TqQuestionItem record);

    TqQuestionItem selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TqQuestionItem record);

    int updateByPrimaryKey(TqQuestionItem record);
}
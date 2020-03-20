package com.elearning.dao.testPaper;

import com.elearning.pojo.testPaper.TpaQuestionItem;

import java.util.List;

public interface TpaQuestionItemMapper {

    int deleteByPrimaryKey(Integer ID);

    int insert(TpaQuestionItem record);

    int insertSelective(TpaQuestionItem record);

    TpaQuestionItem selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TpaQuestionItem record);

    int updateByPrimaryKey(TpaQuestionItem record);

    List<TpaQuestionItem> getListByQuestionId(Integer questionId);

}
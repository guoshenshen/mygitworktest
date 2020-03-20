package com.elearning.dao.testQuestions;

import com.elearning.pojo.testQuestions.QcKnowledge;

public interface QcKnowledgeMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(QcKnowledge record);

    int insertSelective(QcKnowledge record);

    QcKnowledge selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(QcKnowledge record);

    int updateByPrimaryKey(QcKnowledge record);
}
package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TeEvaQuestionnaireItemChoice;

public interface TeEvaQuestionnaireItemChoiceMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TeEvaQuestionnaireItemChoice record);

    int insertSelective(TeEvaQuestionnaireItemChoice record);

    TeEvaQuestionnaireItemChoice selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TeEvaQuestionnaireItemChoice record);

    int updateByPrimaryKey(TeEvaQuestionnaireItemChoice record);
}
package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TeEvaQuestionnaireItemAnswer;

public interface TeEvaQuestionnaireItemAnswerMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TeEvaQuestionnaireItemAnswer record);

    int insertSelective(TeEvaQuestionnaireItemAnswer record);

    TeEvaQuestionnaireItemAnswer selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TeEvaQuestionnaireItemAnswer record);

    int updateByPrimaryKey(TeEvaQuestionnaireItemAnswer record);
}
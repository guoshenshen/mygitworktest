package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TeEvaQuestionnaireItem;

public interface TeEvaQuestionnaireItemMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TeEvaQuestionnaireItem record);

    int insertSelective(TeEvaQuestionnaireItem record);

    TeEvaQuestionnaireItem selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TeEvaQuestionnaireItem record);

    int updateByPrimaryKeyWithBLOBs(TeEvaQuestionnaireItem record);

    int updateByPrimaryKey(TeEvaQuestionnaireItem record);
}
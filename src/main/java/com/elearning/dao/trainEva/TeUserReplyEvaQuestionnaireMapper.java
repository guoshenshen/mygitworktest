package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TeUserReplyEvaQuestionnaire;

public interface TeUserReplyEvaQuestionnaireMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TeUserReplyEvaQuestionnaire record);

    int insertSelective(TeUserReplyEvaQuestionnaire record);

    TeUserReplyEvaQuestionnaire selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TeUserReplyEvaQuestionnaire record);

    int updateByPrimaryKey(TeUserReplyEvaQuestionnaire record);
}
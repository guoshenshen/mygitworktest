package com.elearning.dao.trainNeeds;

import com.elearning.pojo.trainNeeds.TnQuestionnAireItemAnswer;

public interface TnQuestionnAireItemAnswerMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TnQuestionnAireItemAnswer record);

    int insertSelective(TnQuestionnAireItemAnswer record);

    TnQuestionnAireItemAnswer selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TnQuestionnAireItemAnswer record);

    int updateByPrimaryKeyWithBLOBs(TnQuestionnAireItemAnswer record);

    int updateByPrimaryKey(TnQuestionnAireItemAnswer record);
}
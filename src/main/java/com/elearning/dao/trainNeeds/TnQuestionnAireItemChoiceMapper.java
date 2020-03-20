package com.elearning.dao.trainNeeds;

import com.elearning.pojo.trainNeeds.TnQuestionnAireItemChoice;

public interface TnQuestionnAireItemChoiceMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TnQuestionnAireItemChoice record);

    int insertSelective(TnQuestionnAireItemChoice record);

    TnQuestionnAireItemChoice selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TnQuestionnAireItemChoice record);

    int updateByPrimaryKey(TnQuestionnAireItemChoice record);
}
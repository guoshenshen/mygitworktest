package com.elearning.dao.trainNeeds;

import com.elearning.pojo.trainNeeds.TnQuestionnAireItem;

public interface TnQuestionnAireItemMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TnQuestionnAireItem record);

    int insertSelective(TnQuestionnAireItem record);

    TnQuestionnAireItem selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TnQuestionnAireItem record);

    int updateByPrimaryKeyWithBLOBs(TnQuestionnAireItem record);

    int updateByPrimaryKey(TnQuestionnAireItem record);
}
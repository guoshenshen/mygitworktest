package com.elearning.dao.trainNeeds;

import com.elearning.pojo.trainNeeds.TnUserReplyQuestionnAire;

public interface TnUserReplyQuestionnAireMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TnUserReplyQuestionnAire record);

    int insertSelective(TnUserReplyQuestionnAire record);

    TnUserReplyQuestionnAire selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TnUserReplyQuestionnAire record);

    int updateByPrimaryKey(TnUserReplyQuestionnAire record);
}
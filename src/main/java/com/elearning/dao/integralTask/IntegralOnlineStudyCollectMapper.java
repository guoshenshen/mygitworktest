package com.elearning.dao.integralTask;

import com.elearning.pojo.integralTask.IntegralOnlineStudyCollect;

public interface IntegralOnlineStudyCollectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IntegralOnlineStudyCollect record);

    int insertSelective(IntegralOnlineStudyCollect record);

    IntegralOnlineStudyCollect selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntegralOnlineStudyCollect record);

    int updateByPrimaryKey(IntegralOnlineStudyCollect record);
}
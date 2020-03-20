package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.OrgRecommendTrain;
import com.elearning.pojo.systemManage.OrgRecommendTrainKey;

public interface OrgRecommendTrainMapper {
    int deleteByPrimaryKey(OrgRecommendTrainKey key);

    int insert(OrgRecommendTrain record);

    int insertSelective(OrgRecommendTrain record);

    OrgRecommendTrain selectByPrimaryKey(OrgRecommendTrainKey key);

    int updateByPrimaryKeySelective(OrgRecommendTrain record);

    int updateByPrimaryKey(OrgRecommendTrain record);
}
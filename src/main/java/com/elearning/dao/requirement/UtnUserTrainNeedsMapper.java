package com.elearning.dao.requirement;

import com.elearning.pojo.requirement.UtnUserTrainNeeds;

public interface UtnUserTrainNeedsMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(UtnUserTrainNeeds record);

    int insertSelective(UtnUserTrainNeeds record);

    UtnUserTrainNeeds selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(UtnUserTrainNeeds record);

    int updateByPrimaryKey(UtnUserTrainNeeds record);
}
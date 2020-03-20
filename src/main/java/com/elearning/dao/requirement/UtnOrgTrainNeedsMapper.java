package com.elearning.dao.requirement;

import com.elearning.pojo.requirement.UtnOrgTrainNeeds;

public interface UtnOrgTrainNeedsMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(UtnOrgTrainNeeds record);

    int insertSelective(UtnOrgTrainNeeds record);

    UtnOrgTrainNeeds selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(UtnOrgTrainNeeds record);

    int updateByPrimaryKey(UtnOrgTrainNeeds record);
}
package com.elearning.dao.trainNeeds;

import com.elearning.pojo.trainNeeds.TnAssignUser;

import java.util.Map;

public interface TnAssignUserMapper {

    int deleteByPrimaryKey(Integer ID);

    int insert(TnAssignUser record);

    int insertSelective(TnAssignUser record);

    TnAssignUser selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TnAssignUser record);

    int updateByPrimaryKey(TnAssignUser record);

    int updateBySurveyIdAndUserIdList(Map<String,Object> condition);


}
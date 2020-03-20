package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TeTrainEvaAssignUser;

public interface TeTrainEvaAssignUserMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TeTrainEvaAssignUser record);

    int insertSelective(TeTrainEvaAssignUser record);

    TeTrainEvaAssignUser selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TeTrainEvaAssignUser record);

    int updateByPrimaryKey(TeTrainEvaAssignUser record);
}
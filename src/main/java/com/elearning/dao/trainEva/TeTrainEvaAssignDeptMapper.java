package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TeTrainEvaAssignDept;

public interface TeTrainEvaAssignDeptMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TeTrainEvaAssignDept record);

    int insertSelective(TeTrainEvaAssignDept record);

    TeTrainEvaAssignDept selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TeTrainEvaAssignDept record);

    int updateByPrimaryKey(TeTrainEvaAssignDept record);
}
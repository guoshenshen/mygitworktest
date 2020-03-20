package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TeTotalEvaAssignDept;

public interface TeTotalEvaAssignDeptMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TeTotalEvaAssignDept record);

    int insertSelective(TeTotalEvaAssignDept record);

    TeTotalEvaAssignDept selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TeTotalEvaAssignDept record);

    int updateByPrimaryKey(TeTotalEvaAssignDept record);
}
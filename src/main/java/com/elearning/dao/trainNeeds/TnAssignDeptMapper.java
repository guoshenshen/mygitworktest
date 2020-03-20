package com.elearning.dao.trainNeeds;

import com.elearning.pojo.trainNeeds.TnAssignDept;

public interface TnAssignDeptMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TnAssignDept record);

    int insertSelective(TnAssignDept record);

    TnAssignDept selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TnAssignDept record);

    int updateByPrimaryKey(TnAssignDept record);
}
package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TeCourseEvaAssignDept;

public interface TeCourseEvaAssignDeptMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TeCourseEvaAssignDept record);

    int insertSelective(TeCourseEvaAssignDept record);

    TeCourseEvaAssignDept selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TeCourseEvaAssignDept record);

    int updateByPrimaryKey(TeCourseEvaAssignDept record);
}
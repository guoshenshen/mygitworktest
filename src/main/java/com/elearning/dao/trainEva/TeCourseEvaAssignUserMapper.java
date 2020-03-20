package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TeCourseEvaAssignUser;

public interface TeCourseEvaAssignUserMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TeCourseEvaAssignUser record);

    int insertSelective(TeCourseEvaAssignUser record);

    TeCourseEvaAssignUser selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TeCourseEvaAssignUser record);

    int updateByPrimaryKey(TeCourseEvaAssignUser record);
}
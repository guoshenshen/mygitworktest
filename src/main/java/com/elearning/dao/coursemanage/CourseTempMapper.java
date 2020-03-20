package com.elearning.dao.coursemanage;

import com.elearning.pojo.coursemanage.CourseTemp;

public interface CourseTempMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseTemp record);

    int insertSelective(CourseTemp record);

    CourseTemp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseTemp record);

    int updateByPrimaryKey(CourseTemp record);
}
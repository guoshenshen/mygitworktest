package com.elearning.dao.pub;

import com.elearning.pojo.pub.CourseElearning;

public interface CourseElearningMapper {
    int deleteByPrimaryKey(Long course_id);

    int insert(CourseElearning record);

    int insertSelective(CourseElearning record);

    CourseElearning selectByPrimaryKey(Long course_id);

    int updateByPrimaryKeySelective(CourseElearning record);

    int updateByPrimaryKey(CourseElearning record);
}
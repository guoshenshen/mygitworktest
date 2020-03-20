package com.elearning.dao.coursemanage;

import com.elearning.pojo.coursemanage.CourseFilter;

public interface CourseFilterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseFilter record);

    int insertSelective(CourseFilter record);

    CourseFilter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseFilter record);

    int updateByPrimaryKey(CourseFilter record);
}
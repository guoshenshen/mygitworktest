package com.elearning.dao.coursemanage;

import com.elearning.pojo.coursemanage.CourseRelevantcourse;

import java.util.List;
import java.util.Map;

public interface CourseRelevantcourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseRelevantcourse record);

    int insertSelective(CourseRelevantcourse record);

    CourseRelevantcourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseRelevantcourse record);

    int updateByPrimaryKey(CourseRelevantcourse record);

    List<CourseRelevantcourse> findByMap(Map<String, Object> map);
}
package com.elearning.dao.coursemanage;

import com.elearning.pojo.coursemanage.CourseVideoConvert;

import java.util.List;

public interface CourseVideoConvertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseVideoConvert record);

    int insertSelective(CourseVideoConvert record);

    CourseVideoConvert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseVideoConvert record);

    int updateByPrimaryKey(CourseVideoConvert record);

    CourseVideoConvert findCourseVideoconvertByCourseId(Long courseId);

    List<CourseVideoConvert> findByCourseId(Long courseId);

}
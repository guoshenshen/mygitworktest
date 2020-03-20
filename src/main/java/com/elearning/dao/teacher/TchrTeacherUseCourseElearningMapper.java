package com.elearning.dao.teacher;

import com.elearning.pojo.teacher.TchrTeacherUseCourseElearning;

public interface TchrTeacherUseCourseElearningMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TchrTeacherUseCourseElearning record);

    int insertSelective(TchrTeacherUseCourseElearning record);

    TchrTeacherUseCourseElearning selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TchrTeacherUseCourseElearning record);

    int updateByPrimaryKey(TchrTeacherUseCourseElearning record);
}
package com.elearning.dao.teacher;

import com.elearning.pojo.teacher.TchrTeacherUseCourse;

import java.util.List;
import java.util.Map;

public interface TchrTeacherUseCourseMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TchrTeacherUseCourse record);

    int insertSelective(TchrTeacherUseCourse record);

    TchrTeacherUseCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TchrTeacherUseCourse record);

    int updateByPrimaryKey(TchrTeacherUseCourse record);

    List<TchrTeacherUseCourse> findByMap(Map<String, Object> map);

    List<TchrTeacherUseCourse> findByTeacherId(Integer teacherId);

}
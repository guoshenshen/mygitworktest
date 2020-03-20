package com.elearning.service.teacher;

import com.elearning.pojo.teacher.TchrTeacherUseCourse;

import java.util.List;


public interface ITchrTeacherUseCourseService {

    TchrTeacherUseCourse selectByPrimaryKey(Integer id);

    List<TchrTeacherUseCourse> findByTeacherId(Integer teacherId);

}

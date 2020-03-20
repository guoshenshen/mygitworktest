package com.elearning.service.coursemanage;

import com.elearning.pojo.coursemanage.CourseCoursetype;

import javax.servlet.http.HttpServletRequest;

public interface ICourseCoursetypeService {

    CourseCoursetype selectByPrimaryKey(Integer ID);

    int insert(CourseCoursetype record);

    void listMySelectCourseToSchedule(HttpServletRequest request);

    void deleteMySelectCourseToSchedule(HttpServletRequest request);

}

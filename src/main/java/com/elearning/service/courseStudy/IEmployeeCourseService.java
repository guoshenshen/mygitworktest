package com.elearning.service.courseStudy;


import com.elearning.vo.logon.CourseStudyForm;

import java.util.List;
import java.util.Map;

public interface IEmployeeCourseService {

    List<CourseStudyForm> listCoursesByOperatorId(Map<String, Object> map);

}

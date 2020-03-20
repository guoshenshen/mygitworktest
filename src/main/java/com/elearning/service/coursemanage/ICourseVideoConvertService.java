package com.elearning.service.coursemanage;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.coursemanage.CourseVideoConvert;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/1 17:12
 */
public interface ICourseVideoConvertService {

    ServiceResponse findCourseVideoconvertByCourseId(Long uploadCourseId);

    CourseVideoConvert getCourseVideoconvertByCourseId(Long uploadCourseId);



}

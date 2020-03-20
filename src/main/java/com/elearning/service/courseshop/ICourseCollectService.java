package com.elearning.service.courseshop;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.coursemanage.CourseRelevantcourse;
import com.elearning.pojo.pub.EosOperator;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/26 9:56
 */
public interface ICourseCollectService {

    ServiceResponse updateCourseCollectStatus(Long courseId, Integer collectId, int orgId, EosOperator operator);

}

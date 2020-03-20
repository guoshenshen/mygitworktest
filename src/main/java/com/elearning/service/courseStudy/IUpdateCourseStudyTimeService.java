package com.elearning.service.courseStudy;

import com.elearning.common.ServiceResponse;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/15 10:11
 */
public interface IUpdateCourseStudyTimeService {
    ServiceResponse updateCourseStudyTime(HttpServletRequest request) throws ParseException;
}

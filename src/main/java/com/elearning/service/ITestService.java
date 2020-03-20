package com.elearning.service;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.pub.Course;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/2 8:53
 */
public interface ITestService {
    /**
     * 根据courseId 查询该门课程的所有信息
     * @param courseId
     * @return
     */
    Course getCourseById(Long courseId);

    ServiceResponse listCourse(Integer num,Integer size);

    ServiceResponse delete(Long courseId);
}

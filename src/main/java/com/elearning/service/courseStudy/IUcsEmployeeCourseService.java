package com.elearning.service.courseStudy;

import com.elearning.pojo.courseStudy.UcsEmployeeCourse;
import com.elearning.vo.CourseStudy.UserJoinedCourseForm;

import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/16 13:42
 */
public interface IUcsEmployeeCourseService {
    /**
     * 根据课程id,年份,当前登录的id查询学习情况
     * @param map
     * @return
     */
    List<UcsEmployeeCourse> findByExample(Map<String,Object> map);

    int updateByPrimaryKeySelective(UcsEmployeeCourse ucsEmployeeCourse);

    int insertSelectCourse(UcsEmployeeCourse ucs);

    UcsEmployeeCourse SelectUcsEmployeecourse(Integer userID,long courseID,Integer currentYear);

    List<UserJoinedCourseForm> getUserCourseProgress(Map<String, Object> map);

    List<UcsEmployeeCourse> findByCourseId(Object courseId);




}

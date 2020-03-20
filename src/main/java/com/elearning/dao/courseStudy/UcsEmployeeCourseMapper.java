package com.elearning.dao.courseStudy;

import com.elearning.pojo.courseStudy.UcsEmployeeCourse;
import com.elearning.vo.CourseStudy.UserJoinedCourseForm;

import java.util.List;
import java.util.Map;

public interface UcsEmployeeCourseMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(UcsEmployeeCourse record);

    int insertSelective(UcsEmployeeCourse record);

    UcsEmployeeCourse selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(UcsEmployeeCourse record);

    int updateByPrimaryKey(UcsEmployeeCourse record);

    List<UcsEmployeeCourse> findByExample(Map<String,Object> map);

    UcsEmployeeCourse findByuserIdAndCourseIdWithoutYear(Map<String,Object> map);

    UcsEmployeeCourse findByuserIdAndCourseId(Map<String,Object> map);

    List<UserJoinedCourseForm> getUserCourseProgress(Map<String, Object> map);

    List<UcsEmployeeCourse> findByCourseId(Object courseId);



}
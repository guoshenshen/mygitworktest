package com.elearning.dao.coursemanage;

import com.elearning.pojo.coursemanage.CourseCoursetype;

import java.util.List;

public interface CourseCoursetypeMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(CourseCoursetype record);

    int insertSelective(CourseCoursetype record);

    CourseCoursetype selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(CourseCoursetype record);

    int updateByPrimaryKey(CourseCoursetype record);

    /**
     * 根据courseId查询courseCourseType
     * @param courseId
     * @return
     */
    List<CourseCoursetype> findCourseCoursetypebyCourseId(Long courseId);
}
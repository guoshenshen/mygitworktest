package com.elearning.dao.courseStudy;

import com.elearning.pojo.courseStudy.VUserSelectCourse;

public interface VUserSelectCourseMapper {
    int insert(VUserSelectCourse record);

    int insertSelective(VUserSelectCourse record);
}
package com.elearning.dao.coursemanage;

import com.elearning.pojo.coursemanage.SheetCourseAvailableThirdparty;

public interface SheetCourseAvailableThirdpartyMapper {
    int deleteByPrimaryKey(String course_id);

    int insert(SheetCourseAvailableThirdparty record);

    int insertSelective(SheetCourseAvailableThirdparty record);

    SheetCourseAvailableThirdparty selectByPrimaryKey(String course_id);

    int updateByPrimaryKeySelective(SheetCourseAvailableThirdparty record);

    int updateByPrimaryKey(SheetCourseAvailableThirdparty record);
}
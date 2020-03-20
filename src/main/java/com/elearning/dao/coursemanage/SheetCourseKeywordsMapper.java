package com.elearning.dao.coursemanage;

import com.elearning.pojo.coursemanage.SheetCourseKeywords;

public interface SheetCourseKeywordsMapper {
    int deleteByPrimaryKey(Long courseId);

    int insert(SheetCourseKeywords record);

    int insertSelective(SheetCourseKeywords record);

    SheetCourseKeywords selectByPrimaryKey(Long courseId);

    int updateByPrimaryKeySelective(SheetCourseKeywords record);

    int updateByPrimaryKey(SheetCourseKeywords record);
}
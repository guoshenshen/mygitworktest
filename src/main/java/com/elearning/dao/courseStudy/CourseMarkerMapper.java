package com.elearning.dao.courseStudy;

import com.elearning.pojo.courseStudy.CourseMarker;

public interface CourseMarkerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseMarker record);

    int insertSelective(CourseMarker record);

    CourseMarker selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseMarker record);

    int updateByPrimaryKey(CourseMarker record);
}
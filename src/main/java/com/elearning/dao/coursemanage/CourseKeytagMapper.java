package com.elearning.dao.coursemanage;

import com.elearning.pojo.coursemanage.CourseKeytagKey;

import java.util.List;

public interface CourseKeytagMapper {
    int deleteByPrimaryKey(CourseKeytagKey key);

    int insert(CourseKeytagKey record);

    int insertSelective(CourseKeytagKey record);


    /**
     * 根据courseId 查询 courseKey
     * @param courseId
     * @return
     */
    List<CourseKeytagKey> findByCourseId(Long courseId);
}
package com.elearning.dao.courseshop;

import com.elearning.pojo.courseshop.CourseCollect;

import java.util.List;
import java.util.Map;

public interface CourseCollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseCollect record);

    int insertSelective(CourseCollect record);

    CourseCollect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseCollect record);

    int updateByPrimaryKey(CourseCollect record);

    List<CourseCollect> findByExample(Map<String, Object> map);

}
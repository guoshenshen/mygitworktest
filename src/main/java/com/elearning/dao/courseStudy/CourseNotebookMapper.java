package com.elearning.dao.courseStudy;

import com.elearning.pojo.courseStudy.CourseNotebook;

import java.util.List;
import java.util.Map;

public interface CourseNotebookMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(CourseNotebook record);

    int insertSelective(CourseNotebook record);

    CourseNotebook selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(CourseNotebook record);

    int updateByPrimaryKey(CourseNotebook record);

    List<CourseNotebook> findByMap(Map<String, Object> map);
}
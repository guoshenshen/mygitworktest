package com.elearning.dao.coursemanage;

import com.elearning.pojo.coursemanage.CourseType;

import java.util.List;
import java.util.Map;

public interface CourseTypeMapper {
    int deleteByPrimaryKey(Integer courseTypeID);

    int insert(CourseType record);

    int insertSelective(CourseType record);

    CourseType selectByPrimaryKey(Integer courseTypeID);

    int updateByPrimaryKeySelective(CourseType record);

    int updateByPrimaryKey(CourseType record);

    /**
     * 根据fullPath模糊查询三级目录分类
     * @param domainId
     * @return
     */
    List<CourseType> listCourseTypeById(Integer domainId);

    CourseType findByCourseId(Long courseId);

    List<CourseType> findByMap(Map<String, Object> map);

    List<CourseType> findByCourseName(String courseName);

    List<String> getCourseTypeNameByCourseId(Long courseId);


}
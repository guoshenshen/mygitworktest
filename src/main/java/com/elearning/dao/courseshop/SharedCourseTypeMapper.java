package com.elearning.dao.courseshop;

import com.elearning.pojo.courseshop.SharedCourseType;

public interface SharedCourseTypeMapper {
    int deleteByPrimaryKey(Integer sharedCourseTypeId);

    int insert(SharedCourseType record);

    int insertSelective(SharedCourseType record);

    SharedCourseType selectByPrimaryKey(Integer sharedCourseTypeId);

    int updateByPrimaryKeySelective(SharedCourseType record);

    int updateByPrimaryKey(SharedCourseType record);
}
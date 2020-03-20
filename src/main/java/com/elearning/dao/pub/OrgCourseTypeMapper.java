package com.elearning.dao.pub;

import com.elearning.pojo.pub.OrgCourseType;

public interface OrgCourseTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrgCourseType record);

    int insertSelective(OrgCourseType record);

    OrgCourseType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrgCourseType record);

    int updateByPrimaryKey(OrgCourseType record);
}
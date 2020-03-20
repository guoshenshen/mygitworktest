package com.elearning.dao.pub;

import com.elearning.pojo.pub.ResourceCourseType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceCourseTypeMapper {
    int insert(ResourceCourseType record);

    int insertSelective(ResourceCourseType record);


    int updateByPrimaryKeySelective(ResourceCourseType record);


    List<ResourceCourseType> findByResourceId(@Param("resourceId") Long resourceId,
                                              @Param("resourceType") Integer resourceType);
}
package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.Resource;

import java.util.List;
import java.util.Map;

public interface ResourceMapper {

    int deleteByPrimaryKey(Integer ID);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Resource> getResourceListByRoleId(Map<String,Object> condition);

    List<Resource> getResourceListByConditionSQL(Map<String,Object> condition);

    List<Resource> findAll();

    List<Resource> getAllResource();

}
package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.RoleResource;

import java.util.List;
import java.util.Map;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    RoleResource selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);

    //通过角色id查询所有相关的资源
    List<RoleResource> getRoleResourceListByRoleId(Integer roleId);

}
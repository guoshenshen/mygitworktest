package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> findByCondition(Map<String, Object> conditionMap);
}
package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.UserRole;

import java.util.List;
import java.util.Map;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<UserRole> getUserRoleListByUserID(Map<String,Object> parm);

    List<UserRole> getUserRoleListByUser_id(Integer user_id);

    List<UserRole> findByCondition(Map<String, Object> condition);
}
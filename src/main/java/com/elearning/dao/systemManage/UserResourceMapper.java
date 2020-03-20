package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.UserResource;

import java.util.HashMap;
import java.util.List;

public interface UserResourceMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(UserResource record);

    int insertSelective(UserResource record);

    UserResource selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(UserResource record);

    int updateByPrimaryKey(UserResource record);

    List<UserResource> findByOperatorId(Integer operatorId);

    //HashMap queryOperatorResourceList(Integer operatorId);

}
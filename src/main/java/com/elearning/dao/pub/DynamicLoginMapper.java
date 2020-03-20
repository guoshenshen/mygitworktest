package com.elearning.dao.pub;

import com.elearning.pojo.pub.DynamicLogin;

public interface DynamicLoginMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(DynamicLogin record);

    int insertSelective(DynamicLogin record);

    DynamicLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DynamicLogin record);

    int updateByPrimaryKey(DynamicLogin record);

    DynamicLogin findByDynamicKey(Long dynamicKey);

}
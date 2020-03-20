package com.elearning.dao.pub;

import com.elearning.pojo.pub.LoginAuth;

public interface LoginAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginAuth record);

    int insertSelective(LoginAuth record);

    LoginAuth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginAuth record);

    int updateByPrimaryKey(LoginAuth record);
}
package com.elearning.dao.pub;

import com.elearning.pojo.pub.ForgetPwd;

public interface ForgetPwdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ForgetPwd record);

    int insertSelective(ForgetPwd record);

    ForgetPwd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ForgetPwd record);

    int updateByPrimaryKey(ForgetPwd record);
}
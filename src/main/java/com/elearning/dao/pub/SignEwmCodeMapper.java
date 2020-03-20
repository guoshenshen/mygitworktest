package com.elearning.dao.pub;

import com.elearning.pojo.pub.SignEwmCode;

public interface SignEwmCodeMapper {
    int deleteByPrimaryKey(Long code);

    int insert(SignEwmCode record);

    int insertSelective(SignEwmCode record);

    SignEwmCode selectByPrimaryKey(Long code);

    int updateByPrimaryKeySelective(SignEwmCode record);

    int updateByPrimaryKey(SignEwmCode record);
}
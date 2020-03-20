package com.elearning.dao.pub;

import com.elearning.pojo.pub.StatClick;

public interface StatClickMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StatClick record);

    int insertSelective(StatClick record);

    StatClick selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StatClick record);

    int updateByPrimaryKey(StatClick record);
}
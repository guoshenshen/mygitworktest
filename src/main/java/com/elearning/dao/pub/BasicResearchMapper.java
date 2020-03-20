package com.elearning.dao.pub;

import com.elearning.pojo.pub.BasicResearch;

public interface BasicResearchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasicResearch record);

    int insertSelective(BasicResearch record);

    BasicResearch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicResearch record);

    int updateByPrimaryKey(BasicResearch record);
}
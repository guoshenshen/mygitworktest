package com.elearning.dao.pub;

import com.elearning.pojo.pub.BackbonePeople;

public interface BackbonePeopleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BackbonePeople record);

    int insertSelective(BackbonePeople record);

    BackbonePeople selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BackbonePeople record);

    int updateByPrimaryKey(BackbonePeople record);
}
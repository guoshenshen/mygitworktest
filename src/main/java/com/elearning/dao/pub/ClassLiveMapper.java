package com.elearning.dao.pub;

import com.elearning.pojo.pub.ClassLive;

public interface ClassLiveMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassLive record);

    int insertSelective(ClassLive record);

    ClassLive selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClassLive record);

    int updateByPrimaryKey(ClassLive record);
}
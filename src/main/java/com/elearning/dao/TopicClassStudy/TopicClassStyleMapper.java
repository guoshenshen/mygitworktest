package com.elearning.dao.TopicClassStudy;

import com.elearning.pojo.TopicClassStudy.TopicClassStyle;

public interface TopicClassStyleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TopicClassStyle record);

    int insertSelective(TopicClassStyle record);

    TopicClassStyle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TopicClassStyle record);

    int updateByPrimaryKey(TopicClassStyle record);
}
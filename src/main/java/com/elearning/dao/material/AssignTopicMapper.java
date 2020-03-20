package com.elearning.dao.material;

import com.elearning.pojo.material.AssignTopic;

import java.util.List;

public interface AssignTopicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssignTopic record);

    int insertSelective(AssignTopic record);

    AssignTopic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssignTopic record);

    int updateByPrimaryKeyWithBLOBs(AssignTopic record);

    int updateByPrimaryKey(AssignTopic record);

    List<AssignTopic> getListByAssignId(Long assignId);

}
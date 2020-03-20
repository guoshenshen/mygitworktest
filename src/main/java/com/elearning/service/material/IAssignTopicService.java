package com.elearning.service.material;


import com.elearning.pojo.material.AssignTopic;

import java.util.List;

public interface IAssignTopicService {

    AssignTopic selectByPrimaryKey(Long id);

    int insert(AssignTopic record);

    int insertSelective(AssignTopic record);

    int updateByPrimaryKeyWithBLOBs(AssignTopic record);

    List<AssignTopic> getListByAssignId(Long assignId);

    int deleteByPrimaryKey(Long id);

}

package com.elearning.dao.material;

import com.elearning.pojo.material.AssignTopicUser;
import com.elearning.vo.material.AssignTopicUserForm;

import java.util.List;
import java.util.Map;

public interface AssignTopicUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssignTopicUser record);

    int insertSelective(AssignTopicUser record);

    AssignTopicUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssignTopicUser record);

    int updateByPrimaryKeyWithBLOBs(AssignTopicUser record);

    int updateByPrimaryKey(AssignTopicUser record);

    List<AssignTopicUserForm> findTopicFormByAssignIdAndOperatorId(Map<String,Object> condition);

    List<AssignTopicUser> getListByOperatorIdAndTopicIdList(Map<String,Object> condition);



}
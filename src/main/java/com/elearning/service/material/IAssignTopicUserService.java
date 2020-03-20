package com.elearning.service.material;


import com.elearning.pojo.material.AssignTopicUser;
import com.elearning.vo.material.AssignTopicUserForm;

import java.util.List;

public interface IAssignTopicUserService {

    AssignTopicUser selectByPrimaryKey(Long id);

    int save(AssignTopicUser record);

    List<AssignTopicUserForm> findTopicFormByAssignIdAndOperatorId(Long assignId, Integer operatorId);

    void deleteByAssignIdAndOperatorId(Long assignId, Integer operatorId);


}

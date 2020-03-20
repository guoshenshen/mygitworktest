package com.elearning.service.material;


import com.elearning.pojo.material.AssignUser;
import com.elearning.vo.BasicUserForm;
import com.elearning.vo.material.AssignUserForm;

import java.util.List;
import java.util.Map;

public interface IAssignUserService {

    AssignUser selectByPrimaryKey(Long id);

    int update(AssignUser record);

    //冷冻（隐藏）某人已收到的调查问卷派送
    void freezeAssignUser(Long assignId,List<Integer> operatorList);

    void unfreezeAssignUser(Long assignId,List<Integer> operatorList);

    List<AssignUser> getListByAssignId(Long assignId);

    List<BasicUserForm> findUserListByConditionSQL(String sqlQuery, Integer tenantId, Map<String, Object> condition, String orderPageString);

    void batchSave(Long assignId,List<Integer> selectedList);

    List<AssignUserForm> getListByTypeAndResourceIdAndOperatorId(Integer type, Long resourceId, Integer assignUserId);

    AssignUser findByAssignIdAndOperatorId(Long assignId, Integer operatorId);

}

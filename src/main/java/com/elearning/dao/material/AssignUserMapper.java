package com.elearning.dao.material;

import com.elearning.pojo.material.AssignUser;
import com.elearning.vo.material.AssignUserForm;

import java.util.List;
import java.util.Map;

public interface AssignUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssignUser record);

    int insertSelective(AssignUser record);

    AssignUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssignUser record);

    int updateByPrimaryKey(AssignUser record);

    int updateByAssignIdAndOperatorList(Map<String,Object> map);

    int updateByAssignIdAndOperatorList2(Map<String,Object> map);

    List<AssignUser> getListByAssignId(Long assignId);

    List<AssignUserForm> getListByTypeAndResourceIdAndOperatorId(Map<String,Object> map);

    List<AssignUser> getListByAssignIdAndOperatorId(Map<String,Object> map);

}
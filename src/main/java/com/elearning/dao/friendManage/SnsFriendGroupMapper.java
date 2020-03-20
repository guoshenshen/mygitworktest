package com.elearning.dao.friendManage;

import com.elearning.pojo.friendManage.SnsFriendGroup;

public interface SnsFriendGroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(SnsFriendGroup record);

    int insertSelective(SnsFriendGroup record);

    SnsFriendGroup selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(SnsFriendGroup record);

    int updateByPrimaryKey(SnsFriendGroup record);
}
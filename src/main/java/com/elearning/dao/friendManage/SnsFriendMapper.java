package com.elearning.dao.friendManage;

import com.elearning.pojo.friendManage.SnsFriend;
import com.elearning.pojo.friendManage.SnsFriendKey;

public interface SnsFriendMapper {
    int deleteByPrimaryKey(SnsFriendKey key);

    int insert(SnsFriend record);

    int insertSelective(SnsFriend record);

    SnsFriend selectByPrimaryKey(SnsFriendKey key);

    int updateByPrimaryKeySelective(SnsFriend record);

    int updateByPrimaryKey(SnsFriend record);
}
package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TeTotalEvaAssignUser;

public interface TeTotalEvaAssignUserMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TeTotalEvaAssignUser record);

    int insertSelective(TeTotalEvaAssignUser record);

    TeTotalEvaAssignUser selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TeTotalEvaAssignUser record);

    int updateByPrimaryKey(TeTotalEvaAssignUser record);
}
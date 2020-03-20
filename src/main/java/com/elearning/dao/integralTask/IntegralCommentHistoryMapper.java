package com.elearning.dao.integralTask;

import com.elearning.pojo.integralTask.IntegralCommentHistory;

public interface IntegralCommentHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IntegralCommentHistory record);

    int insertSelective(IntegralCommentHistory record);

    IntegralCommentHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntegralCommentHistory record);

    int updateByPrimaryKey(IntegralCommentHistory record);
}
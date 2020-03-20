package com.elearning.dao.integralTask;

import com.elearning.pojo.integralTask.IntegralSignInHistory;

public interface IntegralSignInHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IntegralSignInHistory record);

    int insertSelective(IntegralSignInHistory record);

    IntegralSignInHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntegralSignInHistory record);

    int updateByPrimaryKey(IntegralSignInHistory record);
}
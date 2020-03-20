package com.elearning.dao.integralTask;

import com.elearning.pojo.integralTask.IntegralSummaryHistory;

import java.util.List;
import java.util.Map;

public interface IntegralSummaryHistoryMapper {

    int deleteByPrimaryKey(Long id);

    int insert(IntegralSummaryHistory record);

    int insertSelective(IntegralSummaryHistory record);

    IntegralSummaryHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntegralSummaryHistory record);

    int updateByPrimaryKey(IntegralSummaryHistory record);

    List<IntegralSummaryHistory> getListByTrainIdAndUserIdAndStatus(Map<String,Object> map);

}
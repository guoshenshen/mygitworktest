package com.elearning.service.integralTask;

import com.elearning.pojo.integralTask.IntegralSummaryHistory;

import java.util.List;
import java.util.Map;

public interface IIntegralSummaryHistoryService {

    IntegralSummaryHistory selectByPrimaryKey(Long id);

    List<IntegralSummaryHistory> getListByTrainIdAndUserIdAndStatus(Map<String,Object> map);

    int saveIntegralSummaryHistory(IntegralSummaryHistory record);


}

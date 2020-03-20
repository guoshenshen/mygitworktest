package com.elearning.dao.plan;

import com.elearning.pojo.plan.TpTrainPlanHistory;
import com.elearning.pojo.plan.TpTrainPlanHistoryWithBLOBs;

public interface TpTrainPlanHistoryMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(TpTrainPlanHistoryWithBLOBs record);

    int insertSelective(TpTrainPlanHistoryWithBLOBs record);

    TpTrainPlanHistoryWithBLOBs selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(TpTrainPlanHistoryWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TpTrainPlanHistoryWithBLOBs record);

    int updateByPrimaryKey(TpTrainPlanHistory record);
}
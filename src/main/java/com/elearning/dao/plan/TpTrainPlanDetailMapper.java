package com.elearning.dao.plan;

import com.elearning.pojo.plan.TpTrainPlanDetail;
import com.elearning.pojo.plan.TpTrainPlanDetailWithBLOBs;

public interface TpTrainPlanDetailMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(TpTrainPlanDetailWithBLOBs record);

    int insertSelective(TpTrainPlanDetailWithBLOBs record);

    TpTrainPlanDetailWithBLOBs selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(TpTrainPlanDetailWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TpTrainPlanDetailWithBLOBs record);

    int updateByPrimaryKey(TpTrainPlanDetail record);
}
package com.elearning.dao.plan;

import com.elearning.pojo.plan.TpTrainPlanApprove;

public interface TpTrainPlanApproveMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TpTrainPlanApprove record);

    int insertSelective(TpTrainPlanApprove record);

    TpTrainPlanApprove selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TpTrainPlanApprove record);

    int updateByPrimaryKey(TpTrainPlanApprove record);
}
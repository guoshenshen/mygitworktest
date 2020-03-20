package com.elearning.dao.plan;

import com.elearning.pojo.plan.TpTrainPlanFile;

public interface TpTrainPlanFileMapper {
    int deleteByPrimaryKey(Long fileID);

    int insert(TpTrainPlanFile record);

    int insertSelective(TpTrainPlanFile record);

    TpTrainPlanFile selectByPrimaryKey(Long fileID);

    int updateByPrimaryKeySelective(TpTrainPlanFile record);

    int updateByPrimaryKey(TpTrainPlanFile record);
}
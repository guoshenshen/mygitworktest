package com.elearning.service.plan;

import com.elearning.pojo.plan.TpTrainPlanDetail;
import com.elearning.pojo.plan.TpTrainPlanDetailWithBLOBs;
import com.elearning.pojo.pub.TrainWithBLOBs;

public interface ITpTrainPlanDetailService {

    TpTrainPlanDetailWithBLOBs findById(Long ID);

    int updateByPrimaryKeySelective(TpTrainPlanDetailWithBLOBs record);

    TrainWithBLOBs castToTrain(TpTrainPlanDetail tpTrainPlanDetail);

}

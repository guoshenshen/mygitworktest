package com.elearning.service.integralTask;

import com.elearning.pojo.integralTask.Integral;
import com.elearning.pojo.mixtraining.MtMixTrainUserTrainInfo;
import com.elearning.pojo.pub.TrainWithBLOBs;

public interface IIntegralService {

    Integral selectByPrimaryKey(Integer id);

    boolean addInnerAndOuterTrainIntegral(MtMixTrainUserTrainInfo mtMixTrainUserTrainInfo, TrainWithBLOBs train);

    boolean addTrainSummaryIntegral(String summaryId,String trainId,Long operatorId,String conclusion);



}

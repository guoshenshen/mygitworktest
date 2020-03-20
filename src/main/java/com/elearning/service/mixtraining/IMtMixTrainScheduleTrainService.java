package com.elearning.service.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainScheduleTrain;

import java.util.List;

public interface IMtMixTrainScheduleTrainService {

    MtMixTrainScheduleTrain selectByPrimaryKey(Integer id);

    List<MtMixTrainScheduleTrain> findScheduleTrainList(Integer trainId);

    int save(MtMixTrainScheduleTrain record);




}

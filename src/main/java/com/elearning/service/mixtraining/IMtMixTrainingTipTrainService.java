package com.elearning.service.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainingTipTrain;
import com.elearning.pojo.mixtraining.MtMixTrainingTipTrainKey;

import java.util.List;

public interface IMtMixTrainingTipTrainService {

    MtMixTrainingTipTrain selectByPrimaryKey(MtMixTrainingTipTrainKey key);

    List<MtMixTrainingTipTrain> findAll();

}

package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainingTipTrain;
import com.elearning.pojo.mixtraining.MtMixTrainingTipTrainKey;

import java.util.List;

public interface MtMixTrainingTipTrainMapper {
    int deleteByPrimaryKey(MtMixTrainingTipTrainKey key);

    int insert(MtMixTrainingTipTrain record);

    int insertSelective(MtMixTrainingTipTrain record);

    MtMixTrainingTipTrain selectByPrimaryKey(MtMixTrainingTipTrainKey key);

    int updateByPrimaryKeySelective(MtMixTrainingTipTrain record);

    int updateByPrimaryKey(MtMixTrainingTipTrain record);

    List<MtMixTrainingTipTrain> findAll();



}
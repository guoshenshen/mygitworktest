package com.elearning.service.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainingTipItem;

import java.util.List;

public interface IMtMixTrainingTipItemService {

    MtMixTrainingTipItem selectByPrimaryKey(Integer id);

    List<MtMixTrainingTipItem> findListByTipId(Integer tipId);

    List<MtMixTrainingTipItem> getMtBeforeTipIteminfoList(List<MtMixTrainingTipItem> tempList);

    List<MtMixTrainingTipItem> getMtAfterTipIteminfoList(List<MtMixTrainingTipItem> tempList);




}

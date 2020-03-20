package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainIngTip;

public interface MtMixTrainIngTipMapper {
    int deleteByPrimaryKey(Integer tipId);

    int insert(MtMixTrainIngTip record);

    int insertSelective(MtMixTrainIngTip record);

    MtMixTrainIngTip selectByPrimaryKey(Integer tipId);

    int updateByPrimaryKeySelective(MtMixTrainIngTip record);

    int updateByPrimaryKey(MtMixTrainIngTip record);
}
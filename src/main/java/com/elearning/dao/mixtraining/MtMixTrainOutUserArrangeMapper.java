package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainOutUserArrange;

public interface MtMixTrainOutUserArrangeMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(MtMixTrainOutUserArrange record);

    int insertSelective(MtMixTrainOutUserArrange record);

    MtMixTrainOutUserArrange selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(MtMixTrainOutUserArrange record);

    int updateByPrimaryKey(MtMixTrainOutUserArrange record);
}
package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainDeptArrange;

public interface MtMixTrainDeptArrangeMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(MtMixTrainDeptArrange record);

    int insertSelective(MtMixTrainDeptArrange record);

    MtMixTrainDeptArrange selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(MtMixTrainDeptArrange record);

    int updateByPrimaryKey(MtMixTrainDeptArrange record);
}
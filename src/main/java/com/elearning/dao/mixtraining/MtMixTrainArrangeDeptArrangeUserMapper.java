package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainArrangeDeptArrangeUser;

public interface MtMixTrainArrangeDeptArrangeUserMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(MtMixTrainArrangeDeptArrangeUser record);

    int insertSelective(MtMixTrainArrangeDeptArrangeUser record);

    MtMixTrainArrangeDeptArrangeUser selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(MtMixTrainArrangeDeptArrangeUser record);

    int updateByPrimaryKey(MtMixTrainArrangeDeptArrangeUser record);
}
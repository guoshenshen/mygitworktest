package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainApprove;

public interface MtMixTrainApproveMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MtMixTrainApprove record);

    int insertSelective(MtMixTrainApprove record);

    MtMixTrainApprove selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MtMixTrainApprove record);

    int updateByPrimaryKey(MtMixTrainApprove record);
}
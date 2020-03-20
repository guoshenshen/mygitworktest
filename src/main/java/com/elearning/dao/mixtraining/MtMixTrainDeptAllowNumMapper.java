package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainDeptAllowNum;

public interface MtMixTrainDeptAllowNumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MtMixTrainDeptAllowNum record);

    int insertSelective(MtMixTrainDeptAllowNum record);

    MtMixTrainDeptAllowNum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MtMixTrainDeptAllowNum record);

    int updateByPrimaryKey(MtMixTrainDeptAllowNum record);
}
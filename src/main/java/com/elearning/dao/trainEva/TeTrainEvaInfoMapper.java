package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TeTrainEvaInfo;

public interface TeTrainEvaInfoMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TeTrainEvaInfo record);

    int insertSelective(TeTrainEvaInfo record);

    TeTrainEvaInfo selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TeTrainEvaInfo record);

    int updateByPrimaryKeyWithBLOBs(TeTrainEvaInfo record);

    int updateByPrimaryKey(TeTrainEvaInfo record);
}
package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TrainInfo;

public interface TrainInfoMapper {
    int insert(TrainInfo record);

    int insertSelective(TrainInfo record);
}
package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.VUserTrain;

public interface VUserTrainMapper {
    int insert(VUserTrain record);

    int insertSelective(VUserTrain record);
}
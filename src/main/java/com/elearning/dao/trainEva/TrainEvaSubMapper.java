package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TrainEvaSub;

public interface TrainEvaSubMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TrainEvaSub record);

    int insertSelective(TrainEvaSub record);

    TrainEvaSub selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TrainEvaSub record);

    int updateByPrimaryKey(TrainEvaSub record);
}
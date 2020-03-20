package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TrainEva;

public interface TrainEvaMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TrainEva record);

    int insertSelective(TrainEva record);

    TrainEva selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TrainEva record);

    int updateByPrimaryKey(TrainEva record);
}
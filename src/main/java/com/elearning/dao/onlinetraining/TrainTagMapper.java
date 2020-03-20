package com.elearning.dao.onlinetraining;

import com.elearning.pojo.onlinetraining.TrainTagKey;

import java.util.List;

public interface TrainTagMapper {
    int deleteByPrimaryKey(TrainTagKey key);

    int insert(TrainTagKey record);

    int insertSelective(TrainTagKey record);

    List<TrainTagKey> findByTrainID(Long trainId);

    TrainTagKey findByTrainIDAndTagID(TrainTagKey key);


}
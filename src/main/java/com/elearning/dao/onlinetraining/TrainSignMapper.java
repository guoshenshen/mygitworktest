package com.elearning.dao.onlinetraining;

import com.elearning.pojo.onlinetraining.TrainSign;
import com.elearning.pojo.onlinetraining.TrainSignWithBLOBs;

import java.util.List;

public interface TrainSignMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TrainSignWithBLOBs record);

    int insertSelective(TrainSignWithBLOBs record);

    TrainSignWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainSignWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TrainSignWithBLOBs record);

    int updateByPrimaryKey(TrainSign record);

    List<TrainSignWithBLOBs> getListByTrainId(Integer trainId);

}
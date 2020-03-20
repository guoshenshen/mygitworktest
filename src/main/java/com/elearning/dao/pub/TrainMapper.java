package com.elearning.dao.pub;

import com.elearning.pojo.pub.Train;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.vo.onlinetraining.BasicTrainForm;

import java.util.List;
import java.util.Map;

public interface TrainMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TrainWithBLOBs record);

    int insertSelective(TrainWithBLOBs record);

    TrainWithBLOBs selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TrainWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TrainWithBLOBs record);

    int updateByPrimaryKey(Train record);

    List<TrainWithBLOBs> listTrains(Map<String,Object> parmMap);

    List<Train> selectByTrainIDAndTenantId(Map<String,Object> parmMap);

    List<TrainWithBLOBs> findByTrainPlan(Long trainPlanID);

    List<TrainWithBLOBs> getListByTopbandId(Integer topbandId);

    List<BasicTrainForm> findByConditionSQL(Map<String, Object> conditions);

    List<TrainWithBLOBs> listTrainByOperatorId(Map<String,Object> parmMap);

}
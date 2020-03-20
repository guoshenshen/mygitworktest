package com.elearning.dao.onlinetraining;

import com.elearning.pojo.onlinetraining.TrainSignRecord;

import java.util.List;

public interface TrainSignRecordMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TrainSignRecord record);

    int insertSelective(TrainSignRecord record);

    TrainSignRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainSignRecord record);

    int updateByPrimaryKey(TrainSignRecord record);

    List<TrainSignRecord> getListBySignId(Integer sign_id);


}
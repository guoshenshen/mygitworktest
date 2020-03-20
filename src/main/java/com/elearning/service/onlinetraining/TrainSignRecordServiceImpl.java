package com.elearning.service.onlinetraining;

import com.elearning.dao.onlinetraining.TrainSignRecordMapper;
import com.elearning.pojo.onlinetraining.TrainSignRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("trainSignRecordService")
public class TrainSignRecordServiceImpl implements ITrainSignRecordService {

    @Autowired
    private TrainSignRecordMapper trainSignRecordMapper;

    @Override
    public TrainSignRecord selectByPrimaryKey(Integer id){

        return this.trainSignRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TrainSignRecord> getListBySignId(Integer sign_id){

        return this.trainSignRecordMapper.getListBySignId(sign_id);
    }








}

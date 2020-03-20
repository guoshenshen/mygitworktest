package com.elearning.service.onlinetraining;

import com.elearning.dao.onlinetraining.TrainSignMapper;
import com.elearning.dao.onlinetraining.TrainTagMapper;
import com.elearning.pojo.onlinetraining.KeyWordsTag;
import com.elearning.pojo.onlinetraining.TrainSign;
import com.elearning.pojo.onlinetraining.TrainSignWithBLOBs;
import com.elearning.pojo.onlinetraining.TrainTagKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("trainSignService")
public class TrainSignServiceImpl implements ITrainSignService {

    @Autowired
    private TrainSignMapper trainSignMapper;

    @Override
    public TrainSignWithBLOBs selectByPrimaryKey(Integer id){

        return  this.trainSignMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TrainSignWithBLOBs> getListByTrainId(Integer trainId){

        return  this.trainSignMapper.getListByTrainId(trainId);
    }








}

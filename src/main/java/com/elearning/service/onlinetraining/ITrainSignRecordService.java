package com.elearning.service.onlinetraining;


import com.elearning.pojo.onlinetraining.TrainSignRecord;

import java.util.List;

public interface ITrainSignRecordService {

    TrainSignRecord selectByPrimaryKey(Integer id);

    List<TrainSignRecord> getListBySignId(Integer sign_id);

}

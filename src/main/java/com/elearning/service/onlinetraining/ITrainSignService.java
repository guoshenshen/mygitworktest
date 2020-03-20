package com.elearning.service.onlinetraining;


import com.elearning.pojo.onlinetraining.TrainSign;
import com.elearning.pojo.onlinetraining.TrainSignWithBLOBs;

import java.util.List;

public interface ITrainSignService {

    TrainSignWithBLOBs selectByPrimaryKey(Integer id);

    List<TrainSignWithBLOBs> getListByTrainId(Integer trainId);



}

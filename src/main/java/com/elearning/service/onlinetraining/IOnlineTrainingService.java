package com.elearning.service.onlinetraining;


import com.elearning.common.ServiceResponse;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.Train;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.vo.onlinetraining.BasicTrainForm;
import com.elearning.vo.onlinetraining.TrainForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IOnlineTrainingService {

    TrainWithBLOBs getOnlineTrainingByID(Integer ID);

    TrainWithBLOBs findById(Integer ID);

    ServiceResponse listTrains(Map<String,Object> parmMap);

    ServiceResponse updatePubStatus(HttpServletRequest request);

    ServiceResponse delTrainById(String[] selectbox);

    int add(TrainForm trainForm, HttpServletRequest request);

    int update(TrainForm trainForm, HttpServletRequest request);

    int updateByPrimaryKeyWithBLOBs(TrainWithBLOBs record);

    TrainForm getTrainForm(Train train, Integer operatorId);

    ServiceResponse getTrainTopBand(Integer trainId);

    ServiceResponse getIfUserJoinTrain(EosOperator eosoperator,Integer trainId);

    Integer forupdate(HttpServletRequest request);

    List<TrainWithBLOBs> getListByTopbandId(Integer topbandId);

    List<BasicTrainForm> findVisibleTrainPageByCondition(Map<String, Object> conditions);

    List<TrainForm> listTrainByOperatorId(Map<String, Object> map);

}

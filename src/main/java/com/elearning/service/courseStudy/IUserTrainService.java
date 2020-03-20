package com.elearning.service.courseStudy;


import com.elearning.common.ServiceResponse;
import com.elearning.pojo.courseStudy.UserTrain;
import com.elearning.pojo.courseStudy.UserTrainKey;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserTrainService {

    UserTrain selectByPrimaryKey(UserTrainKey key);

    int deleteByPrimaryKey(UserTrainKey key);

    int save(UserTrain record);

    int deleteByTrainID(Integer trainId);

    List<UserTrain> getListByTrainId(Integer trainId);

    boolean ifOperatorJoinTrain(Integer operatorId, Integer trainId);

    ServiceResponse selectSingleCourse(Long courseId, HttpServletRequest request);

    void updateTrainInfo(int train_id,String trainName,String startTime,String endTime,String sponsorName,int trainWay,Boolean isStationTrain);



}

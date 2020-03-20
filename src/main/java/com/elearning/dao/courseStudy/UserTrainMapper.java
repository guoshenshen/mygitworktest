package com.elearning.dao.courseStudy;

import com.elearning.pojo.courseStudy.UserTrain;
import com.elearning.pojo.courseStudy.UserTrainKey;

import java.util.List;
import java.util.Map;

public interface UserTrainMapper {

    int deleteByPrimaryKey(UserTrainKey key);

    int insert(UserTrain record);

    int insertSelective(UserTrain record);

    UserTrain selectByPrimaryKey(UserTrainKey key);

    int updateByPrimaryKeySelective(UserTrain record);

    int updateByPrimaryKey(UserTrain record);

    int deleteByTrainID(Integer trainId);

    List<UserTrain> getListByTrainId(Integer trainId);

    List<UserTrain> getListByTrainIdAndOperatorId(Map<String,Object> parmMap);

}
package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.VTrainEvaUser;

public interface VTrainEvaUserMapper {
    int insert(VTrainEvaUser record);

    int insertSelective(VTrainEvaUser record);
}
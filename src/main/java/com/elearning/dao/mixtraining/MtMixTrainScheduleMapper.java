package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainSchedule;

import java.util.HashMap;
import java.util.Map;

public interface MtMixTrainScheduleMapper {
    int deleteByPrimaryKey(Integer scheduleId);

    int insert(MtMixTrainSchedule record);

    int insertSelective(MtMixTrainSchedule record);

    MtMixTrainSchedule selectByPrimaryKey(Integer scheduleId);

    int updateByPrimaryKeySelective(MtMixTrainSchedule record);

    int updateByPrimaryKey(MtMixTrainSchedule record);



}
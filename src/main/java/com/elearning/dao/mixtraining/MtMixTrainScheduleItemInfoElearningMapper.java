package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainScheduleItemInfoElearning;

public interface MtMixTrainScheduleItemInfoElearningMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MtMixTrainScheduleItemInfoElearning record);

    int insertSelective(MtMixTrainScheduleItemInfoElearning record);

    MtMixTrainScheduleItemInfoElearning selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MtMixTrainScheduleItemInfoElearning record);

    int updateByPrimaryKey(MtMixTrainScheduleItemInfoElearning record);
}
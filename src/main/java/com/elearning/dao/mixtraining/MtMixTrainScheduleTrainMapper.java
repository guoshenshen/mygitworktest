package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainScheduleTrain;

import java.util.List;

public interface MtMixTrainScheduleTrainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MtMixTrainScheduleTrain record);

    int insertSelective(MtMixTrainScheduleTrain record);

    MtMixTrainScheduleTrain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MtMixTrainScheduleTrain record);

    int updateByPrimaryKey(MtMixTrainScheduleTrain record);

    List<MtMixTrainScheduleTrain> findScheduleTrainList(Integer trainId);




}
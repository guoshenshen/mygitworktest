package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainingTipItem;

import java.util.List;

public interface MtMixTrainingTipItemMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(MtMixTrainingTipItem record);

    int insertSelective(MtMixTrainingTipItem record);

    MtMixTrainingTipItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MtMixTrainingTipItem record);

    int updateByPrimaryKey(MtMixTrainingTipItem record);

    List<MtMixTrainingTipItem> findListByTipId(Integer tipId);


}
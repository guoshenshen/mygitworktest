package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainSectionFile;

public interface MtMixTrainSectionFileMapper {

    int deleteByPrimaryKey(Integer ID);

    int insert(MtMixTrainSectionFile record);

    int insertSelective(MtMixTrainSectionFile record);

    MtMixTrainSectionFile selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(MtMixTrainSectionFile record);

    int updateByPrimaryKey(MtMixTrainSectionFile record);
}
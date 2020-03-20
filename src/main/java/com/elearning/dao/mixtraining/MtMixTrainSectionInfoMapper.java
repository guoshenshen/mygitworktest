package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainSectionInfo;
import com.elearning.pojo.mixtraining.MtMixTrainSectionInfoWithBLOBs;

public interface MtMixTrainSectionInfoMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(MtMixTrainSectionInfoWithBLOBs record);

    int insertSelective(MtMixTrainSectionInfoWithBLOBs record);

    MtMixTrainSectionInfoWithBLOBs selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(MtMixTrainSectionInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MtMixTrainSectionInfoWithBLOBs record);

    int updateByPrimaryKey(MtMixTrainSectionInfo record);
}
package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainPhoto;

public interface MtMixTrainPhotoMapper {

    int deleteByPrimaryKey(Long photoId);

    int insert(MtMixTrainPhoto record);

    int insertSelective(MtMixTrainPhoto record);

    MtMixTrainPhoto selectByPrimaryKey(Long photoId);

    int updateByPrimaryKeySelective(MtMixTrainPhoto record);

    int updateByPrimaryKey(MtMixTrainPhoto record);
}
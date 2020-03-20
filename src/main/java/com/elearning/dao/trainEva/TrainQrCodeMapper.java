package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TrainQrCode;

public interface TrainQrCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrainQrCode record);

    int insertSelective(TrainQrCode record);

    TrainQrCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainQrCode record);

    int updateByPrimaryKey(TrainQrCode record);
}
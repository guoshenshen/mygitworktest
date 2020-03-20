package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.TrainQrCodeUser;

public interface TrainQrCodeUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrainQrCodeUser record);

    int insertSelective(TrainQrCodeUser record);

    TrainQrCodeUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainQrCodeUser record);

    int updateByPrimaryKey(TrainQrCodeUser record);
}
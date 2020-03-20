package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainCertificateUser;

public interface MtMixTrainCertificateUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MtMixTrainCertificateUser record);

    int insertSelective(MtMixTrainCertificateUser record);

    MtMixTrainCertificateUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MtMixTrainCertificateUser record);

    int updateByPrimaryKey(MtMixTrainCertificateUser record);
}
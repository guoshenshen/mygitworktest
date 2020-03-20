package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainCertificateOptions;

public interface MtMixTrainCertificateOptionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MtMixTrainCertificateOptions record);

    int insertSelective(MtMixTrainCertificateOptions record);

    MtMixTrainCertificateOptions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MtMixTrainCertificateOptions record);

    int updateByPrimaryKey(MtMixTrainCertificateOptions record);
}
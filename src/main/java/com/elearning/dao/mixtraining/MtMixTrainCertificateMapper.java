package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainCertificate;

public interface MtMixTrainCertificateMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(MtMixTrainCertificate record);

    int insertSelective(MtMixTrainCertificate record);

    MtMixTrainCertificate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MtMixTrainCertificate record);

    int updateByPrimaryKey(MtMixTrainCertificate record);
}
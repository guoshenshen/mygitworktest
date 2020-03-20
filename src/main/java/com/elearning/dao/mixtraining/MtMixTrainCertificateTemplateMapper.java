package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainCertificateTemplate;

import java.util.List;

public interface MtMixTrainCertificateTemplateMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(MtMixTrainCertificateTemplate record);

    int insertSelective(MtMixTrainCertificateTemplate record);

    MtMixTrainCertificateTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MtMixTrainCertificateTemplate record);

    int updateByPrimaryKey(MtMixTrainCertificateTemplate record);

    List<MtMixTrainCertificateTemplate> findByOperatorId(Integer operatorId);


}
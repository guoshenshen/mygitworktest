package com.elearning.service.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainCertificateTemplate;

import java.util.List;

public interface IMtMixTrainCertificateTemplateService {

    MtMixTrainCertificateTemplate selectByPrimaryKey(Integer id);

    List<MtMixTrainCertificateTemplate> findByOperatorId(Integer operatorId);


}

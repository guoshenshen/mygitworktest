package com.elearning.service.mixtraining;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.mixtraining.MtMixTrainCertificate;

import javax.servlet.http.HttpServletRequest;

public interface IMtMixTrainCertificateService {

    MtMixTrainCertificate selectByPrimaryKey(Integer id);

    ServiceResponse findTrainSign(HttpServletRequest request);

    ServiceResponse findTrainSignEwm(HttpServletRequest request);

    ServiceResponse getOrgList(HttpServletRequest request);

    ServiceResponse getCertificateTemplate(HttpServletRequest request);




}

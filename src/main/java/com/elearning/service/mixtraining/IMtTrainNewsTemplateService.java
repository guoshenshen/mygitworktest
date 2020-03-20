package com.elearning.service.mixtraining;

import com.elearning.pojo.mixtraining.MtTrainNewsTemplate;
import com.elearning.vo.mixtraining.MtTrainNewsTemplateForm;

import javax.servlet.http.HttpServletRequest;

public interface IMtTrainNewsTemplateService {

    MtTrainNewsTemplate selectByPrimaryKey(Integer newsTemplateId);


    void queryNewsTemplate(MtTrainNewsTemplateForm mtMixTrainNewsTemplateForm, HttpServletRequest request);




}

package com.elearning.service.mixtraining;

import com.elearning.vo.CourseStudy.TrainSummaryForm;

import javax.servlet.http.HttpServletRequest;

public interface IMtMixTrainEffectService {

     int add(TrainSummaryForm trainSummaryForm ,HttpServletRequest request);

     int update(TrainSummaryForm trainSummaryForm ,HttpServletRequest request);

     int delete(TrainSummaryForm trainSummaryForm ,HttpServletRequest request);




}

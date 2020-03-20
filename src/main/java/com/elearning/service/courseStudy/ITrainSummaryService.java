package com.elearning.service.courseStudy;


import com.elearning.common.ServiceResponse;
import com.elearning.pojo.courseStudy.TrainSummaryWithBLOBs;
import com.elearning.vo.CourseStudy.TrainSummaryForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ITrainSummaryService {

    TrainSummaryWithBLOBs selectByPrimaryKey(Integer id);

    List<TrainSummaryWithBLOBs> findByExample(Map<String,Object> parmMap);

    TrainSummaryForm getTrainSummaryForm(TrainSummaryWithBLOBs trainSummary);

    int saveTrainSummaryForm(TrainSummaryForm trainSummaryForm);

    int updateTrainSummaryForm(TrainSummaryForm trainSummaryForm);

    void saveAttachFile(TrainSummaryForm trainSummaryForm);

    int deleteSummaryForms(String[] delete_ids);

    Map<String,Object> getTrainingSummary(HttpServletRequest request);

    List<TrainSummaryForm> getTrainSummaryFormList(List<TrainSummaryWithBLOBs> trainSummaryList);

    ServiceResponse saveOrupdateSubmit(HttpServletRequest request);

    ServiceResponse deleteSummary(HttpServletRequest request);

    ServiceResponse showTrainSummary(HttpServletRequest request);


}

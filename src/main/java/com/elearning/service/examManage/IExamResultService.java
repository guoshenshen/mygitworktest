package com.elearning.service.examManage;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.examManage.ExamResult;
import com.elearning.vo.examManage.ExamOnlinePaperReadingForm;

import java.util.List;
import java.util.Map;

public interface IExamResultService {

    ExamResult selectByPrimaryKey(Integer id);

    List<ExamOnlinePaperReadingForm> findExamOnlinePaperReadingByExamId(Integer eid);

    ServiceResponse searchExamScoreList(Map<String,Object> queryConditionMap);


}

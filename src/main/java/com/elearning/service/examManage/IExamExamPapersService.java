package com.elearning.service.examManage;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.examManage.ExamExamPapers;

import java.util.List;
import java.util.Map;

public interface IExamExamPapersService {

    ExamExamPapers selectByPrimaryKey(Integer ID);

    List<ExamExamPapers> findByExamId(Integer examID);

    ExamExamPapers findByExamIdReturnExamExamPapers(Integer examID);

    ServiceResponse searchPaperList(Map<String,Object> queryConditionMap);

    int update(ExamExamPapers record);

    int insert(ExamExamPapers record);



}

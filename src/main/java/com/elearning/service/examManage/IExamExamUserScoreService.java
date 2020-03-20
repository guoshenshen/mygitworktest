package com.elearning.service.examManage;

import com.elearning.pojo.examManage.ExamExamUserScore;

import java.util.List;

public interface IExamExamUserScoreService {

    ExamExamUserScore selectByPrimaryKey(Integer ID);

    List<ExamExamUserScore> findByExamId(Integer examId);


}

package com.elearning.dao.examManage;

import com.elearning.pojo.examManage.ExamPaperScoreAnalyze;

public interface ExamPaperScoreAnalyzeMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(ExamPaperScoreAnalyze record);

    int insertSelective(ExamPaperScoreAnalyze record);

    ExamPaperScoreAnalyze selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(ExamPaperScoreAnalyze record);

    int updateByPrimaryKey(ExamPaperScoreAnalyze record);
}
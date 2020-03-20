package com.elearning.dao.examManage;

import com.elearning.pojo.examManage.ExamExamUserScore;

import java.util.List;

public interface ExamExamUserScoreMapper {

    int deleteByPrimaryKey(Integer ID);

    int insert(ExamExamUserScore record);

    int insertSelective(ExamExamUserScore record);

    ExamExamUserScore selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(ExamExamUserScore record);

    int updateByPrimaryKey(ExamExamUserScore record);

    List<ExamExamUserScore> findByExamId(Integer examId);

}
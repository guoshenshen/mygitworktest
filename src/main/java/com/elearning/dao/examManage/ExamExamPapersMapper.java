package com.elearning.dao.examManage;

import com.elearning.pojo.examManage.ExamExamPapers;

import java.util.List;

public interface ExamExamPapersMapper {

    int deleteByPrimaryKey(Integer ID);

    int insert(ExamExamPapers record);

    int insertSelective(ExamExamPapers record);

    ExamExamPapers selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(ExamExamPapers record);

    int updateByPrimaryKey(ExamExamPapers record);

    List<ExamExamPapers> findByExamId(Integer examID);


}
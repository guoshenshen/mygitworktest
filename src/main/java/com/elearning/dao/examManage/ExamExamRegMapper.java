package com.elearning.dao.examManage;

import com.elearning.pojo.examManage.ExamExamReg;

import java.util.List;
import java.util.Map;

public interface ExamExamRegMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(ExamExamReg record);

    int insertSelective(ExamExamReg record);

    ExamExamReg selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(ExamExamReg record);

    int updateByPrimaryKey(ExamExamReg record);

    List<ExamExamReg> findByUserIdAndExamStatus(Map<String,Object> parmMap);

    List<ExamExamReg> getListByExamIdAndExamStatus(Map<String,Object> parmMap);

}
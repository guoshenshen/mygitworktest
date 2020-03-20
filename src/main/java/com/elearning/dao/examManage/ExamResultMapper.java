package com.elearning.dao.examManage;

import com.elearning.pojo.examManage.ExamResult;

public interface ExamResultMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ExamResult record);

    int insertSelective(ExamResult record);

    ExamResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamResult record);

    int updateByPrimaryKeyWithBLOBs(ExamResult record);

    int updateByPrimaryKey(ExamResult record);
}
package com.elearning.dao.examManage;

import com.elearning.pojo.examManage.ExamExamDept;
import com.elearning.vo.examManage.ExamDeptForm;

import java.util.List;

public interface ExamExamDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamExamDept record);

    int insertSelective(ExamExamDept record);

    ExamExamDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamExamDept record);

    int updateByPrimaryKey(ExamExamDept record);

    List<ExamDeptForm> findByExamId(Integer eid);

}
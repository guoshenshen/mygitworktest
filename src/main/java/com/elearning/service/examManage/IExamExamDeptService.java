package com.elearning.service.examManage;

import com.elearning.pojo.examManage.ExamExamDept;
import com.elearning.vo.examManage.ExamDeptForm;

import java.util.List;

public interface IExamExamDeptService {

    ExamExamDept selectByPrimaryKey(Integer id);

    List<ExamDeptForm> queryExamDeptByExamId(Integer eid);


}

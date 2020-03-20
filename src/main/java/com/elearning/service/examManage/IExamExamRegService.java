package com.elearning.service.examManage;

import com.elearning.pojo.examManage.ExamExamReg;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IExamExamRegService {

    ExamExamReg selectByPrimaryKey(Integer ID);

    List<ExamExamReg> findByUserIdAndExamStatus(Integer employeeID, Integer examStatus);

    ExamExamReg findByExamRegId(Integer examRegId);

    void auditExamRegByIds(HttpServletRequest request);

    int findRegCount(Integer eid, Integer examStatus);

}

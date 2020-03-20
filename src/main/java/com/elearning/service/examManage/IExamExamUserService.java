package com.elearning.service.examManage;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.examManage.ExamExamUser;
import com.elearning.vo.examManage.ExamUserForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IExamExamUserService {

    ExamExamUser selectByPrimaryKey(Integer id);

    ServiceResponse removeExamUser(HttpServletRequest request);

    ServiceResponse insertExamUser(HttpServletRequest request);

    boolean isExistExamUser(ExamExamUser examUser);

    int saveExamUser(ExamExamUser record);

    List<ExamUserForm> queryExamUserByExamId(Integer eid);


}

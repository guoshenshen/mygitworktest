package com.elearning.service.examManage;

import com.elearning.common.ServiceResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IExamManageService {

    ServiceResponse searchExamList(Map<String,Object> queryConditionMap,HttpServletRequest request);

    ServiceResponse sendExamNoticetoUser(HttpServletRequest request);


}

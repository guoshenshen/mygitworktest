package com.elearning.service.material;


import com.elearning.common.ServiceResponse;
import com.elearning.pojo.material.Assignment;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAssignmentService {

    List<Assignment> getAssignmentByIdReturnList(Long id);

    List<Assignment> findByResourceId(Integer type,Long resourceId);

    ServiceResponse updateAssignInfo(HttpServletRequest request);

    ServiceResponse deleteAssignment(HttpServletRequest request);

    ServiceResponse showTopicsOfAssign(HttpServletRequest request);

    ServiceResponse updateTopic(HttpServletRequest request);

    ServiceResponse deleteTopicByBatch(HttpServletRequest request);

    ServiceResponse loadAssignUsers(HttpServletRequest request);

    ServiceResponse freezeUserDistribute(HttpServletRequest request);

    ServiceResponse getAssignInfo(HttpServletRequest request);

    ServiceResponse distributeAssignByMail(HttpServletRequest request);

    void showAssignmentListForStudent(HttpServletRequest request);

    void intoAssignmentForUser(HttpServletRequest request);

    ServiceResponse submitAssignment(HttpServletRequest request);




}

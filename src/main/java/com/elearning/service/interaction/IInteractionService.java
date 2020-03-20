package com.elearning.service.interaction;

import com.elearning.common.ServiceResponse;

import javax.servlet.http.HttpServletRequest;

public interface IInteractionService {

    void intoUserZone(HttpServletRequest request);

    ServiceResponse getBasicFollowingInfo(HttpServletRequest request);

    ServiceResponse listCoursesOfSpecifiedUser(HttpServletRequest request);

    ServiceResponse listTrainsOfSpecifiedUser(HttpServletRequest request);




}

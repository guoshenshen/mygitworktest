package com.elearning.service.notificationPush;

import com.elearning.common.ServiceResponse;

import javax.servlet.http.HttpServletRequest;

public interface ITrainingHourReminderService {

    ServiceResponse getTrainingHour(HttpServletRequest request);


}

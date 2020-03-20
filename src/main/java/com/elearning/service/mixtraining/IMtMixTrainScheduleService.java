package com.elearning.service.mixtraining;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.mixtraining.MtMixTrainSchedule;
import com.elearning.vo.mixtraining.MtMixTrainScheduleItemForm;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IMtMixTrainScheduleService {

    MtMixTrainSchedule selectByPrimaryKey(Integer scheduleId);

    String listScheduleItem(HttpServletRequest request);

    void forAddOnLineScheduleItemInfo(HttpServletRequest request);

    void forAddOffLineScheduleItemInfo(MtMixTrainScheduleItemForm mtMixTrainScheduleItemForm, HttpServletRequest request);

    int deleteAjax(HttpServletRequest request);

    ServiceResponse editScheduleItem(HttpServletRequest request);

    void saveOrUpdateUserNeedLearnCourse(Map<String,Object> map);

    void updateOnLineScheduleItem(HttpServletRequest request);

    void updateOffLineScheduleItem(MtMixTrainScheduleItemForm mtMixTrainScheduleItemForm,HttpServletRequest request);

    void listScheduleTeacher(HttpServletRequest request);

    void listScheduleCourse(HttpServletRequest request);

    ServiceResponse loadOnlineTrainScheduleItem(HttpServletRequest request);



}

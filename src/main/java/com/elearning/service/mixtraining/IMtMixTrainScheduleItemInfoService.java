package com.elearning.service.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainScheduleItemInfo;
import com.elearning.pojo.pub.Course;
import com.elearning.vo.mixtraining.MtMixTrainScheduleItemForm;
import com.elearning.vo.teacher.TchrBaseInfoForm;

import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/31 10:09
 */
public interface IMtMixTrainScheduleItemInfoService {

    int synTeacher(MtMixTrainScheduleItemInfo mtScheduleItemForm, int orgId, int operatorId);

    int synTeacher(MtMixTrainScheduleItemForm mtScheduleItemForm, int orgId, int operatorId);

    void dropScheduleCourseAndTeacher(MtMixTrainScheduleItemInfo mtScheduleItemForm, int operatorFlag);

    void dropScheduleCourseAndTeacher(MtMixTrainScheduleItemForm mtScheduleItemForm, int operatorFlag);

    List<MtMixTrainScheduleItemInfo> findByMap(Map<String, Object> map);

    int update(MtMixTrainScheduleItemInfo scheduleItem);

    List<MtMixTrainScheduleItemInfo> findByScheduleId(Integer scheduleId);

    List<MtMixTrainScheduleItemInfo> getScheduleOnlineItemList(List<MtMixTrainScheduleItemInfo> scheduleItemList);

    List<MtMixTrainScheduleItemInfo> getScheduleOfflineItemList(List<MtMixTrainScheduleItemInfo> scheduleItemList);

    //培训日程中线上课程list
    List<Course> getCourseListByTrainId(int trainId);

    //培训日程中线下课程list
    List<Course> getOfflineCourseList(int scheduleId);

    boolean judgeCourseIdInSchedule(int scheduleId,long courseId);

    void saveOrUpdate(MtMixTrainScheduleItemInfo temp);

    long synCourse(MtMixTrainScheduleItemForm scheduleItemForm, int orgId, String operatorName);

    int insert(MtMixTrainScheduleItemInfo record);

    MtMixTrainScheduleItemInfo findById(Integer id);

    int deleteByPrimaryKey(Integer id);

    List<TchrBaseInfoForm> getTeacherFormListByScheduleId(int scheduleId);

    List<String> ListSortByscheduleId(Integer scheduleId);

}

package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainScheduleItemInfo;
import com.elearning.pojo.pub.Course;

import java.util.List;
import java.util.Map;

public interface MtMixTrainScheduleItemInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MtMixTrainScheduleItemInfo record);

    int insertSelective(MtMixTrainScheduleItemInfo record);

    MtMixTrainScheduleItemInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MtMixTrainScheduleItemInfo record);

    int updateByPrimaryKey(MtMixTrainScheduleItemInfo record);

    List<MtMixTrainScheduleItemInfo> findByMap(Map<String, Object> map);

    List<MtMixTrainScheduleItemInfo> findByScheduleId(Integer scheduleId);

    List<Course> findCourseListByTrainId(int trainId);  //培训日程中线上课程list

    List<MtMixTrainScheduleItemInfo> getListBySchAndCouAndOnAndOpe(Map<String, Object> map);

    //List<String> ListSortByscheduleId(Integer scheduleId);




}
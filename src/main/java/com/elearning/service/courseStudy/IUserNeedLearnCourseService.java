package com.elearning.service.courseStudy;


import com.elearning.pojo.courseStudy.UserNeedLearnCourse;
import com.elearning.pojo.courseStudy.UserNeedLearnCourseKey;

import java.util.List;
import java.util.Map;

public interface IUserNeedLearnCourseService {

    int deleteByTrainID(Integer trainId);

    UserNeedLearnCourse findCourseRelatedTrainId(Map<String,Object> map);

    boolean findIfUserneedlearnTheCourse(Map<String, Object> map);

    int insert(UserNeedLearnCourse userNeedLearnCourse);

    void updateTrainName(int train_id,String trainName);

    List<UserNeedLearnCourse> getListByOperatorAndTrainIdAndYear(Map<String,Object> map);

    int deleteByPrimaryKey(UserNeedLearnCourseKey key);

    void deleteByOperatorIdTrainIdYear(Integer operatorId,Integer trainId,String year);

    UserNeedLearnCourse selectByPrimaryKey(UserNeedLearnCourseKey key);

    int updateByPrimaryKey(UserNeedLearnCourse record);

    List<UserNeedLearnCourse> findAll();

    void deleteByCourseIdTrainId(Long course_id,Integer trainId);


}

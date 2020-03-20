package com.elearning.dao.courseStudy;

import com.elearning.pojo.courseStudy.UserNeedLearnCourse;
import com.elearning.pojo.courseStudy.UserNeedLearnCourseKey;

import java.util.List;
import java.util.Map;

public interface UserNeedLearnCourseMapper {
    int deleteByPrimaryKey(UserNeedLearnCourseKey key);

    int insert(UserNeedLearnCourse record);

    int insertSelective(UserNeedLearnCourse record);

    UserNeedLearnCourse selectByPrimaryKey(UserNeedLearnCourseKey key);

    int updateByPrimaryKeySelective(UserNeedLearnCourse record);

    int updateByPrimaryKey(UserNeedLearnCourse record);

    int deleteByTrainID(Integer trainId);

    UserNeedLearnCourse findCourseRelatedTrainId(Map<String,Object> map);

    List<UserNeedLearnCourse> findListByTrainId(Integer train_id);

    List<UserNeedLearnCourse> getListByOperatorAndTrainIdAndYear(Map<String,Object> map);

    List<UserNeedLearnCourse> findAll();

    List<UserNeedLearnCourse> findByCourseIdTrainId(Map<String,Object> map);


}
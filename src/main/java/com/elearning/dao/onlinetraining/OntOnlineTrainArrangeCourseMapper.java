package com.elearning.dao.onlinetraining;

import com.elearning.pojo.onlinetraining.OntOnlineTrainArrangeCourse;

public interface OntOnlineTrainArrangeCourseMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(OntOnlineTrainArrangeCourse record);

    int insertSelective(OntOnlineTrainArrangeCourse record);

    OntOnlineTrainArrangeCourse selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(OntOnlineTrainArrangeCourse record);

    int updateByPrimaryKey(OntOnlineTrainArrangeCourse record);
}
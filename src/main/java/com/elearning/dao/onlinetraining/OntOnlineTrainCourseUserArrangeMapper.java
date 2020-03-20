package com.elearning.dao.onlinetraining;

import com.elearning.pojo.onlinetraining.OntOnlineTrainCourseUserArrange;

public interface OntOnlineTrainCourseUserArrangeMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(OntOnlineTrainCourseUserArrange record);

    int insertSelective(OntOnlineTrainCourseUserArrange record);

    OntOnlineTrainCourseUserArrange selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(OntOnlineTrainCourseUserArrange record);

    int updateByPrimaryKey(OntOnlineTrainCourseUserArrange record);

    String getMaxID();
}
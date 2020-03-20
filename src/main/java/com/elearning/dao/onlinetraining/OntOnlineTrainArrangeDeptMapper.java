package com.elearning.dao.onlinetraining;

import com.elearning.pojo.onlinetraining.OntOnlineTrainArrangeDept;

public interface OntOnlineTrainArrangeDeptMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(OntOnlineTrainArrangeDept record);

    int insertSelective(OntOnlineTrainArrangeDept record);

    OntOnlineTrainArrangeDept selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(OntOnlineTrainArrangeDept record);

    int updateByPrimaryKey(OntOnlineTrainArrangeDept record);
}
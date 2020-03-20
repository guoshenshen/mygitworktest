package com.elearning.dao.onlinetraining;

import com.elearning.pojo.onlinetraining.OntOnlineTrainArrangeDeptAllowNum;

public interface OntOnlineTrainArrangeDeptAllowNumMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(OntOnlineTrainArrangeDeptAllowNum record);

    int insertSelective(OntOnlineTrainArrangeDeptAllowNum record);

    OntOnlineTrainArrangeDeptAllowNum selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(OntOnlineTrainArrangeDeptAllowNum record);

    int updateByPrimaryKey(OntOnlineTrainArrangeDeptAllowNum record);
}
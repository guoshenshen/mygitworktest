package com.elearning.dao.onlinetraining;

import com.elearning.pojo.onlinetraining.OntOnlineTrainArrangeDeptArrangeUser;

public interface OntOnlineTrainArrangeDeptArrangeUserMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(OntOnlineTrainArrangeDeptArrangeUser record);

    int insertSelective(OntOnlineTrainArrangeDeptArrangeUser record);

    OntOnlineTrainArrangeDeptArrangeUser selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(OntOnlineTrainArrangeDeptArrangeUser record);

    int updateByPrimaryKey(OntOnlineTrainArrangeDeptArrangeUser record);
}
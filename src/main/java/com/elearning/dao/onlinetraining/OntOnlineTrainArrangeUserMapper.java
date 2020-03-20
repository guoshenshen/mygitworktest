package com.elearning.dao.onlinetraining;

import com.elearning.pojo.onlinetraining.OntOnlineTrainArrangeUser;

public interface OntOnlineTrainArrangeUserMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(OntOnlineTrainArrangeUser record);

    int insertSelective(OntOnlineTrainArrangeUser record);

    OntOnlineTrainArrangeUser selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(OntOnlineTrainArrangeUser record);

    int updateByPrimaryKey(OntOnlineTrainArrangeUser record);
}
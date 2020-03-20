package com.elearning.dao.onlinetraining;

import com.elearning.pojo.onlinetraining.OntOnlineTrainArrangeFile;

public interface OntOnlineTrainArrangeFileMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(OntOnlineTrainArrangeFile record);

    int insertSelective(OntOnlineTrainArrangeFile record);

    OntOnlineTrainArrangeFile selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(OntOnlineTrainArrangeFile record);

    int updateByPrimaryKey(OntOnlineTrainArrangeFile record);
}
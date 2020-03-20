package com.elearning.dao.pub;

import com.elearning.pojo.pub.TrainFormHistoryWithBLOBs;

public interface TrainFormHistoryMapper {
    int insert(TrainFormHistoryWithBLOBs record);

    int insertSelective(TrainFormHistoryWithBLOBs record);
}
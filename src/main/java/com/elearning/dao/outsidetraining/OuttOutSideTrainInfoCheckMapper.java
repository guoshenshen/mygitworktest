package com.elearning.dao.outsidetraining;

import com.elearning.pojo.outsidetraining.OuttOutSideTrainInfoCheck;
import com.elearning.pojo.outsidetraining.OuttOutSideTrainInfoCheckWithBLOBs;

public interface OuttOutSideTrainInfoCheckMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(OuttOutSideTrainInfoCheckWithBLOBs record);

    int insertSelective(OuttOutSideTrainInfoCheckWithBLOBs record);

    OuttOutSideTrainInfoCheckWithBLOBs selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(OuttOutSideTrainInfoCheckWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(OuttOutSideTrainInfoCheckWithBLOBs record);

    int updateByPrimaryKey(OuttOutSideTrainInfoCheck record);
}
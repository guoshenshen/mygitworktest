package com.elearning.dao.integralTask;

import com.elearning.pojo.integralTask.IntegralOfflineTrainRecord;

import java.util.List;
import java.util.Map;

public interface IntegralOfflineTrainRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(IntegralOfflineTrainRecord record);

    int insertSelective(IntegralOfflineTrainRecord record);

    IntegralOfflineTrainRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntegralOfflineTrainRecord record);

    int updateByPrimaryKey(IntegralOfflineTrainRecord record);

    List<IntegralOfflineTrainRecord> getListByUserIdAndTrainIdAndStatus(Map<String,Object> map);



}
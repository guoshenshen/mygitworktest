package com.elearning.service.integralTask;

import com.elearning.pojo.integralTask.IntegralOfflineTrainRecord;

import java.util.List;
import java.util.Map;

public interface IIntegralOfflineTrainRecordService {

    IntegralOfflineTrainRecord selectByPrimaryKey(Long id);

    List<IntegralOfflineTrainRecord> getListByUserIdAndTrainIdAndStatus(Map<String,Object> map);

    int saveAndGetIntegralOfflineTrainRecord(IntegralOfflineTrainRecord record);

    int updateIntegralOfflineTrainRecord(IntegralOfflineTrainRecord record);

}

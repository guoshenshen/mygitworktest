package com.elearning.service.integralTask;

import com.elearning.pojo.integralTask.IntegralRecord;

import java.util.List;
import java.util.Map;

public interface IIntegralRecordService {

    IntegralRecord selectByPrimaryKey(Long id);

    List<IntegralRecord> findNewestRecord();

    int saveIntegralRecord(IntegralRecord record);

    List<IntegralRecord> getListByStudentIdAndHistoryNameAndHistoryId(Map<String,Object> parmMap);

    int updateIntegralRecord(IntegralRecord record);

    List<IntegralRecord> getListByMap(Map<String,Object> parmMap);



}

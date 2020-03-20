package com.elearning.dao.integralTask;

import com.elearning.pojo.integralTask.IntegralRecord;

import java.util.List;
import java.util.Map;

public interface IntegralRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(IntegralRecord record);

    int insertSelective(IntegralRecord record);

    IntegralRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntegralRecord record);

    int updateByPrimaryKey(IntegralRecord record);

    List<IntegralRecord> findNewestRecord();

    List<IntegralRecord> getListByStudentIdAndHistoryNameAndHistoryId(Map<String,Object> parmMap );

    List<IntegralRecord> getListByMap(Map<String,Object> parmMap );




}
package com.elearning.service.statistics;

import com.elearning.pojo.statistics.UscUserRecordStatics;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface IUscUserRecordStaticsService {

    UscUserRecordStatics selectByPrimaryKey(Long recordId);

    void averageDistributeTimeToMonth(Integer operatorId, Integer year, Date startTime, Date endTime, Double classHour);

    List<UscUserRecordStatics> findByExample(Map<String,Object> parmMap);

    int insertSelective(UscUserRecordStatics record);

    int updateByPrimaryKeySelective(UscUserRecordStatics record);

    int deleteByPrimaryKey(Long recordId);


}

package com.elearning.dao.statistics;

import com.elearning.pojo.statistics.UscUserRecordStatics;

import java.util.List;
import java.util.Map;

public interface UscUserRecordStaticsMapper {

    int deleteByPrimaryKey(Long recordId);

    int insert(UscUserRecordStatics record);

    int insertSelective(UscUserRecordStatics record);

    UscUserRecordStatics selectByPrimaryKey(Long recordId);

    int updateByPrimaryKeySelective(UscUserRecordStatics record);

    int updateByPrimaryKey(UscUserRecordStatics record);

    List<UscUserRecordStatics> findByExample(Map<String,Object> parmMap);

}
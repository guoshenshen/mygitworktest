package com.elearning.dao.statistics;

import com.elearning.pojo.statistics.UscUserLearnStatics;
import com.elearning.pojo.statistics.UscUserLearnStaticsKey;
import com.elearning.vo.statistics.UscUserLearnStaticsYearSumDouble;

import java.util.Map;

public interface UscUserLearnStaticsMapper {

    int deleteByPrimaryKey(UscUserLearnStaticsKey key);

    int insert(UscUserLearnStatics record);

    int insertSelective(UscUserLearnStatics record);

    UscUserLearnStatics selectByPrimaryKey(UscUserLearnStaticsKey key);

    int updateByPrimaryKeySelective(UscUserLearnStatics record);

    int updateByPrimaryKey(UscUserLearnStatics record);

    UscUserLearnStaticsYearSumDouble queryUserlearnstaticsInOneYear(Map<String,Object> parmMap);


}
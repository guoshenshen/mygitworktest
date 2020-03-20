package com.elearning.dao.integralTask;

import com.elearning.pojo.integralTask.IntegralRule;

import java.util.List;
import java.util.Map;

public interface IntegralRuleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(IntegralRule record);

    int insertSelective(IntegralRule record);

    IntegralRule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntegralRule record);

    int updateByPrimaryKey(IntegralRule record);

    List<IntegralRule> findListByStatusAndItemCode(Map<String,Object> map);



}
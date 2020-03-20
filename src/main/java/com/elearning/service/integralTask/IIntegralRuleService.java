package com.elearning.service.integralTask;

import com.elearning.pojo.integralTask.IntegralRule;

import java.util.List;
import java.util.Map;

public interface IIntegralRuleService {

    IntegralRule selectByPrimaryKey(Long id);

    List<IntegralRule> findListByStatusAndItemCode(Map<String,Object> map);

}

package com.elearning.service.integralTask;

import com.elearning.dao.integralTask.IntegralRuleMapper;
import com.elearning.pojo.integralTask.IntegralRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("integralRuleService")
public class IntegralRuleService implements IIntegralRuleService{

    @Autowired
    private IntegralRuleMapper integralRuleMapper;

    @Override
    public IntegralRule selectByPrimaryKey(Long id){
        return this.integralRuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<IntegralRule> findListByStatusAndItemCode(Map<String,Object> map){
        return this.integralRuleMapper.findListByStatusAndItemCode(map);
    }







}

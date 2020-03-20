package com.elearning.service.testPaper;

import com.elearning.dao.testPaper.TpaPaperStrategyQuesTypeMapper;
import com.elearning.pojo.testPaper.TpaPaperStrategyQuesType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("tpaPaperStrategyQuesTypeService")
public class TpaPaperStrategyQuesTypeService implements ITpaPaperStrategyQuesTypeService{


    @Autowired
    private TpaPaperStrategyQuesTypeMapper tpaPaperStrategyQuesTypeMapper;


    @Override
    @Transactional(rollbackFor = {Exception.class })
    public TpaPaperStrategyQuesType selectByPrimaryKey(Integer ID){

        return this.tpaPaperStrategyQuesTypeMapper.selectByPrimaryKey(ID);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<TpaPaperStrategyQuesType> findByStrategyId(Integer strategyId){

        return this.tpaPaperStrategyQuesTypeMapper.findByStrategyId(strategyId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<TpaPaperStrategyQuesType> getListByStrategyIdAndStrategyQuesSeq(Map<String,Object> parmMap){

        return this.tpaPaperStrategyQuesTypeMapper.getListByStrategyIdAndStrategyQuesSeq(parmMap);
    }




}

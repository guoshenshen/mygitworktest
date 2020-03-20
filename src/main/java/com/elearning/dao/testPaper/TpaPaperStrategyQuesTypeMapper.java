package com.elearning.dao.testPaper;

import com.elearning.pojo.testPaper.TpaPaperStrategyQuesType;

import java.util.List;
import java.util.Map;

public interface TpaPaperStrategyQuesTypeMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TpaPaperStrategyQuesType record);

    int insertSelective(TpaPaperStrategyQuesType record);

    TpaPaperStrategyQuesType selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TpaPaperStrategyQuesType record);

    int updateByPrimaryKey(TpaPaperStrategyQuesType record);

    List<TpaPaperStrategyQuesType> findByStrategyId(Integer strategyId);

    List<TpaPaperStrategyQuesType> getListByStrategyIdAndStrategyQuesSeq(Map<String,Object> parmMap);




}
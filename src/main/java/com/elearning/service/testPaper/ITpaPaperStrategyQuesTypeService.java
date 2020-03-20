package com.elearning.service.testPaper;

import com.elearning.pojo.testPaper.TpaPaperStrategyQuesType;

import java.util.List;
import java.util.Map;

public interface ITpaPaperStrategyQuesTypeService {

    TpaPaperStrategyQuesType selectByPrimaryKey(Integer ID);

    List<TpaPaperStrategyQuesType> findByStrategyId(Integer strategyId);

    List<TpaPaperStrategyQuesType> getListByStrategyIdAndStrategyQuesSeq(Map<String,Object> parmMap);

}

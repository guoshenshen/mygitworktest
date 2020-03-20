package com.elearning.service.testPaper;

import com.elearning.pojo.testPaper.TpaPaperQuestions;
import com.elearning.pojo.testPaper.TpaPapers;
import com.elearning.vo.testPaper.TpaPaperStrategyQuesTypeForm;

import java.util.List;
import java.util.Map;

public interface ITpaPapersService {

    TpaPapers selectByPrimaryKey(Integer ID);

    List<TpaPapers> findTpaPapersByCondition(Map<String,Object> parmMap);

    TpaPapers findPaperById(Integer id);

    List<TpaPaperStrategyQuesTypeForm> getTpaPaperForClassification(TpaPapers tpaPaper, List<TpaPaperQuestions> tpaPaperQuestionsList);

}

package com.elearning.service.testPaper;

import com.elearning.pojo.testPaper.TpaPaperQuestions;
import com.elearning.pojo.testPaper.TpaPapers;
import com.elearning.vo.testPaper.TpaPaperStrategyQuesTypeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service("paperManageService")
public class PaperManageService implements IPaperManageService{

    @Autowired
    private ITpaPapersService tpaPapersService;

    @Autowired
    private ITpaPaperQuestionsService tpaPaperQuestionsService;

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<TpaPaperStrategyQuesTypeForm> previewTestPaper(HttpServletRequest request){

        String paperId = request.getParameter("paperId");

        TpaPapers testPaper = this.tpaPapersService.findPaperById(Integer.parseInt(paperId));
        List<TpaPaperQuestions> tpaPaperQuestionsList = tpaPaperQuestionsService.findByPaperIdOrderBySequence(testPaper.getID());
        List<TpaPaperStrategyQuesTypeForm> tpaPaperStrategyquestypeFormList = this.tpaPapersService.getTpaPaperForClassification(testPaper,tpaPaperQuestionsList);

        return tpaPaperStrategyquestypeFormList;

    }







}

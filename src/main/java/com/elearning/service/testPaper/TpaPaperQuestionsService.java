package com.elearning.service.testPaper;

import com.elearning.dao.testPaper.TpaPaperQuestionsMapper;
import com.elearning.pojo.testPaper.TpaPaperQuestions;
import com.elearning.pojo.testPaper.TpaQuestionItem;
import com.elearning.pojo.testPaper.TpaQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("tpaPaperQuestionsService")
public class TpaPaperQuestionsService implements ITpaPaperQuestionsService{


    @Autowired
    private TpaPaperQuestionsMapper tpaPaperQuestionsMapper;

    @Autowired
    private ITpaQuestionsService tpaQuestionsService;

    @Autowired
    private ITpaQuestionItemService tpaQuestionItemService;


    @Override
    @Transactional(rollbackFor = {Exception.class })
    public TpaPaperQuestions selectByPrimaryKey(Integer ID){

        return this.tpaPaperQuestionsMapper.selectByPrimaryKey(ID);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<TpaPaperQuestions> findByPaperIdOrderBySequence(Integer paperId){

        List<TpaPaperQuestions> tpaPaperQuestionsList = this.tpaPaperQuestionsMapper.findByPaperIdOrderBySequence(paperId);

        for(TpaPaperQuestions tpaPaperQuestions : tpaPaperQuestionsList){
            List<TpaQuestionItem> tpaQuestionItem = this.tpaQuestionItemService.getListByQuestionId(tpaPaperQuestions.getQuestion_id());
            TpaQuestions tpaQuestions = this.tpaQuestionsService.selectByPrimaryKey(tpaPaperQuestions.getQuestion_id());
            tpaQuestions.setItems(tpaQuestionItem);
            tpaPaperQuestions.setTpaQuestions(tpaQuestions);
        }

        return tpaPaperQuestionsList;
    }


}

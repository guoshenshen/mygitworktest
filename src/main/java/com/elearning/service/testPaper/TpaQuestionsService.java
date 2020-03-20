package com.elearning.service.testPaper;

import com.elearning.dao.testPaper.TpaQuestionsMapper;
import com.elearning.pojo.testPaper.TpaQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("tpaQuestionsService")
public class TpaQuestionsService implements ITpaQuestionsService{


    @Autowired
    private TpaQuestionsMapper tpaQuestionsMapper;


    @Override
    @Transactional(rollbackFor = {Exception.class })
    public TpaQuestions selectByPrimaryKey(Integer ID){

        return this.tpaQuestionsMapper.selectByPrimaryKey(ID);
    }




}

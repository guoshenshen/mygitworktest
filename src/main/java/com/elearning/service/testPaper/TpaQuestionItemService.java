package com.elearning.service.testPaper;

import com.elearning.dao.testPaper.TpaQuestionItemMapper;
import com.elearning.pojo.testPaper.TpaQuestionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("tpaQuestionItemService")
public class TpaQuestionItemService implements ITpaQuestionItemService{


    @Autowired
    private TpaQuestionItemMapper tpaQuestionItemMapper;


    @Override
    @Transactional(rollbackFor = {Exception.class })
    public TpaQuestionItem selectByPrimaryKey(Integer ID){

        return this.tpaQuestionItemMapper.selectByPrimaryKey(ID);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<TpaQuestionItem> getListByQuestionId(Integer questionId){

        return this.tpaQuestionItemMapper.getListByQuestionId(questionId);
    }




}

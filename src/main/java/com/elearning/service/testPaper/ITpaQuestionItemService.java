package com.elearning.service.testPaper;

import com.elearning.pojo.testPaper.TpaQuestionItem;

import java.util.List;

public interface ITpaQuestionItemService {

    TpaQuestionItem selectByPrimaryKey(Integer ID);

    List<TpaQuestionItem> getListByQuestionId(Integer questionId);


}

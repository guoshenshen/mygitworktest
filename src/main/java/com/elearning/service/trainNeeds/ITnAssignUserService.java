package com.elearning.service.trainNeeds;

import com.elearning.pojo.trainNeeds.TnAssignUser;

import java.util.Collection;

public interface ITnAssignUserService {

    TnAssignUser selectByPrimaryKey(Integer ID);

    /**
     * 冷冻（隐藏）某人已收到的调查问卷派送
     * @param surveyId
     * @param operatorList
     */
    void freezeAssignUser(Integer surveyId,Collection<Integer> operatorList);


}

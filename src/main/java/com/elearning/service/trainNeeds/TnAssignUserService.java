package com.elearning.service.trainNeeds;

import com.elearning.dao.trainNeeds.TnAssignUserMapper;
import com.elearning.pojo.trainNeeds.TnAssignUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Service("tnAssignUserService")
public class TnAssignUserService implements ITnAssignUserService {


    @Autowired
    private TnAssignUserMapper tnAssignUserMapper;

    @Override
    public TnAssignUser selectByPrimaryKey(Integer ID){
        return this.tnAssignUserMapper.selectByPrimaryKey(ID);
    }

    @Override
    public void freezeAssignUser(Integer surveyId,Collection<Integer> operatorList){

        Map<String,Object> condition=new HashMap<String,Object>();
        condition.put("surveyId", surveyId);
        condition.put("userIdList",operatorList);

        this.tnAssignUserMapper.updateBySurveyIdAndUserIdList(condition);
    }




}

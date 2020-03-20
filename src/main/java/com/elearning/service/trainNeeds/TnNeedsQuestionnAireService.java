package com.elearning.service.trainNeeds;

import com.elearning.dao.trainNeeds.TnNeedsQuestionnAireMapper;
import com.elearning.pojo.trainNeeds.TnNeedsQuestionnAire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("tnNeedsQuestionnAireService")
public class TnNeedsQuestionnAireService implements ITnNeedsQuestionnAireService {


    @Autowired
    private TnNeedsQuestionnAireMapper tnNeedsQuestionnAireMapper;

    @Override
    public TnNeedsQuestionnAire selectByPrimaryKey(Integer ID){
        return this.tnNeedsQuestionnAireMapper.selectByPrimaryKey(ID);
    }

    @Override
    public List<TnNeedsQuestionnAire> getMyTnNeedsList(Map<String,Object> _map){

        List<TnNeedsQuestionnAire> tnNeedsQuestionnAireList = new ArrayList<>();

        return tnNeedsQuestionnAireList;
    }

    @Override
    public List<TnNeedsQuestionnAire> findByTrainId(Integer trainId){

        return this.tnNeedsQuestionnAireMapper.findByTrainId(trainId);
    }


}

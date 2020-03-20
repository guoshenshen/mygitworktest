package com.elearning.service.trainNeeds;

import com.elearning.pojo.trainNeeds.TnNeedsQuestionnAire;

import java.util.List;
import java.util.Map;

public interface ITnNeedsQuestionnAireService {

    TnNeedsQuestionnAire selectByPrimaryKey(Integer ID);

    List<TnNeedsQuestionnAire> getMyTnNeedsList(Map<String,Object> _map);

    List<TnNeedsQuestionnAire> findByTrainId(Integer trainId);


}

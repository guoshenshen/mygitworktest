package com.elearning.dao.trainNeeds;

import com.elearning.pojo.trainNeeds.TnNeedsQuestionnAire;

import java.util.List;

public interface TnNeedsQuestionnAireMapper {

    int deleteByPrimaryKey(Integer ID);

    int insert(TnNeedsQuestionnAire record);

    int insertSelective(TnNeedsQuestionnAire record);

    TnNeedsQuestionnAire selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TnNeedsQuestionnAire record);

    int updateByPrimaryKeyWithBLOBs(TnNeedsQuestionnAire record);

    int updateByPrimaryKey(TnNeedsQuestionnAire record);

    List<TnNeedsQuestionnAire> findByTrainId(Integer trainId);


}
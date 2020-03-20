package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtTrainNewsTemplate;

import java.util.List;
import java.util.Map;

public interface MtTrainNewsTemplateMapper {

    int deleteByPrimaryKey(Integer newsTemplateId);

    int insert(MtTrainNewsTemplate record);

    int insertSelective(MtTrainNewsTemplate record);

    MtTrainNewsTemplate selectByPrimaryKey(Integer newsTemplateId);

    int updateByPrimaryKeySelective(MtTrainNewsTemplate record);

    int updateByPrimaryKeyWithBLOBs(MtTrainNewsTemplate record);

    int updateByPrimaryKey(MtTrainNewsTemplate record);

    List<MtTrainNewsTemplate> listNewsTemplate(Map<String,Object> map);


}
package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtTrainNews;
import com.elearning.pojo.mixtraining.MtTrainNewsWithBLOBs;

import java.util.List;
import java.util.Map;

public interface MtTrainNewsMapper {
    int deleteByPrimaryKey(Integer newsId);

    int insert(MtTrainNewsWithBLOBs record);

    int insertSelective(MtTrainNewsWithBLOBs record);

    MtTrainNewsWithBLOBs selectByPrimaryKey(Integer newsId);

    int updateByPrimaryKeySelective(MtTrainNewsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MtTrainNewsWithBLOBs record);

    int updateByPrimaryKey(MtTrainNews record);

    List<MtTrainNewsWithBLOBs> findByTrainId(Integer trainId);

    List<MtTrainNewsWithBLOBs> listNews(Map<String,Object> map);

    List<MtTrainNewsWithBLOBs> findListByTrainIdOrderByCreateTime(Integer trainId);

}
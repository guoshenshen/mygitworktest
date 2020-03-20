package com.elearning.dao.courseStudy;

import com.elearning.pojo.courseStudy.TrainSummary;
import com.elearning.pojo.courseStudy.TrainSummaryWithBLOBs;

import java.util.List;
import java.util.Map;

public interface TrainSummaryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TrainSummaryWithBLOBs record);

    int insertSelective(TrainSummaryWithBLOBs record);

    TrainSummaryWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainSummaryWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TrainSummaryWithBLOBs record);

    int updateByPrimaryKey(TrainSummary record);

    List<TrainSummaryWithBLOBs> findByExample(Map<String,Object> parmMap);

    List<TrainSummaryWithBLOBs> queryUserTrainSummaryList(Map<String,Object> parmMap);



}
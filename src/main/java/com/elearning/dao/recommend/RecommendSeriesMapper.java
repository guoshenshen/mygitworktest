package com.elearning.dao.recommend;

import com.elearning.pojo.recommend.RecommendSeries;

import java.util.List;
import java.util.Map;

public interface RecommendSeriesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecommendSeries record);

    int insertSelective(RecommendSeries record);

    RecommendSeries selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecommendSeries record);

    int updateByPrimaryKey(RecommendSeries record);

    List<RecommendSeries> getSeriesListByMap(Map<String, Object> conditionMap);

    List<RecommendSeries> findByConditionSQL(Map<String, Object> conditionMap);
}
package com.elearning.service.recommend;


import com.elearning.pojo.recommend.RecommendSeries;

import java.util.List;
import java.util.Map;

public interface IRecommendSeriesService {

    RecommendSeries selectByPrimaryKey(Integer id);

    List<RecommendSeries> getSeriesList(Map<String, Object> conditionMap);

    int add(RecommendSeries series);

    int update(RecommendSeries series);

    int delete(Integer seriesId);

    List<RecommendSeries> findVisibleSeriesByCondition(Map<String, Object> conditionMap);

}

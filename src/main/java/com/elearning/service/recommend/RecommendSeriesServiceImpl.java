package com.elearning.service.recommend;

import com.elearning.dao.recommend.RecommendSeriesMapper;
import com.elearning.pojo.recommend.RecommendSeries;
import com.elearning.service.recommend.IRecommendSeriesService;
import com.elearning.util.ToolsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("recommendSeriesService")
public class RecommendSeriesServiceImpl implements IRecommendSeriesService {

    @Autowired
    private RecommendSeriesMapper recommendSeriesMapper;

    public RecommendSeries selectByPrimaryKey(Integer id){

        return recommendSeriesMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RecommendSeries> getSeriesList(Map<String, Object> conditionMap) {
        return recommendSeriesMapper.getSeriesListByMap(conditionMap);
    }

    @Override
    public int add(RecommendSeries series) {
        return recommendSeriesMapper.insertSelective(series);
    }

    @Override
    public int update(RecommendSeries series) {
        return recommendSeriesMapper.updateByPrimaryKeySelective(series);
    }

    @Override
    public int delete(Integer seriesId) {
        return recommendSeriesMapper.deleteByPrimaryKey(seriesId);
    }

    @Override
    public List<RecommendSeries> findVisibleSeriesByCondition(Map<String, Object> conditionMap) {
        List<String> orgSEQ = ToolsUtil.orgSEQList(conditionMap.get("orgSEQ").toString());
        conditionMap.put("orgSEQ",orgSEQ);
        if(conditionMap.get("showAll")!= null ){
            conditionMap.put("showAll",Boolean.valueOf(conditionMap.get("showAll").toString()));
        }
        return recommendSeriesMapper.findByConditionSQL(conditionMap);
    }

}

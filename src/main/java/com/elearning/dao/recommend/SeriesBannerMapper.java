package com.elearning.dao.recommend;

import com.elearning.pojo.recommend.SeriesBanner;

import java.util.List;
import java.util.Map;

public interface SeriesBannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SeriesBanner record);

    int insertSelective(SeriesBanner record);

    SeriesBanner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SeriesBanner record);

    int updateByPrimaryKey(SeriesBanner record);

    List<SeriesBanner> findSeriesBannerByCondition(Map<String, Object> map);
}
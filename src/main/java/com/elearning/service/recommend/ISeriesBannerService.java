package com.elearning.service.recommend;


import com.elearning.pojo.recommend.SeriesBanner;

import java.util.List;
import java.util.Map;

public interface ISeriesBannerService {

    SeriesBanner selectByPrimaryKey(Integer id);

    List<SeriesBanner> findSeriesBannerByCondition(Map<String, Object> map);

    int delete(SeriesBanner seriesBanner);

    int insert(SeriesBanner seriesBanner);
}

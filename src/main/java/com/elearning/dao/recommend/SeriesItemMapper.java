package com.elearning.dao.recommend;

import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.Train;
import com.elearning.pojo.recommend.RecommendSeries;
import com.elearning.pojo.recommend.SeriesItem;
import com.elearning.vo.onlinetraining.BasicTrainForm;
import com.elearning.vo.recommend.SeriesItemDTO;

import java.util.List;
import java.util.Map;

public interface SeriesItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SeriesItem record);

    int insertSelective(SeriesItem record);

    SeriesItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SeriesItem record);

    int updateByPrimaryKey(SeriesItem record);

    List<SeriesItem> findByMap(Map<String, Object> condition);

    List<Course> findCourseByCondition(Map<String, Object> conditionForCourseSearch);

    List<SeriesItemDTO> findTrainDtoByCondition(Map<String, Object> condition);

    List<SeriesItemDTO> findCourseDtoByCondition(Map<String, Object> condition);

    List<SeriesItemDTO> findSeriesDtoByCondition(Map<String, Object> condition);

    List<Map<String, Object>> getSeriesItemTypeDistribution(Integer seriesId);

    List<BasicTrainForm> findTrainByCondition(Map<String, Object> condition);

    List<RecommendSeries> findSeriesByCondition(Map<String, Object> condition);
}
package com.elearning.service.recommend;


import com.elearning.common.ServiceResponse;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.Train;
import com.elearning.pojo.recommend.RecommendSeries;
import com.elearning.pojo.recommend.SeriesItem;
import com.elearning.vo.onlinetraining.BasicTrainForm;
import com.elearning.vo.recommend.SeriesItemDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ISeriesItemService {

    SeriesItem selectByPrimaryKey(Long id);

    ServiceResponse getCourseListUnderSameSeries(Long courseId, HttpServletRequest request);

    List<SeriesItem> findByMap(Map<String, Object> seriesitemCondition);

    int delete(Long id);

    List<SeriesItemDTO> conjQuery(String conjTable ,Map<String, Object> condition);

    int save(SeriesItem seriesItem);

    int update(SeriesItem seriesItem);

    Map<Integer,Long> getSeriesItemTypeDistribution(Integer seriesId);

    List<Course> findCourseByCondition(Map<String, Object> condition);

    List<BasicTrainForm> findTrainByCondition(Map<String, Object> condition);

    List<RecommendSeries> findSeriesByCondition(Map<String, Object> condition);
}

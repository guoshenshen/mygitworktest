package com.elearning.service.TopicClassStudy;


import com.elearning.pojo.TopicClassStudy.TopicBanner;

import java.util.List;
import java.util.Map;

public interface ITopicBannerService {

    TopicBanner selectByPrimaryKey(Integer id);

    List<TopicBanner> findListByMap(Map<String, Object> map);

    int update(TopicBanner banner);

    int save(TopicBanner banner);

    int delete(Integer bannerId);

    List<TopicBanner> findOrderedBanner(Map<String, Object> topicBannerCondition);
}

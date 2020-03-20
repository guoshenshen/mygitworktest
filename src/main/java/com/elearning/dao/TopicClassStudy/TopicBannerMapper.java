package com.elearning.dao.TopicClassStudy;

import com.elearning.pojo.TopicClassStudy.TopicBanner;

import java.util.List;
import java.util.Map;

public interface TopicBannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TopicBanner record);

    int insertSelective(TopicBanner record);

    TopicBanner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TopicBanner record);

    int updateByPrimaryKey(TopicBanner record);

    List<TopicBanner> findListByMap(Map<String, Object> map);

    List<TopicBanner> findOrderedBanner(Map<String, Object> topicBannerCondition);
}
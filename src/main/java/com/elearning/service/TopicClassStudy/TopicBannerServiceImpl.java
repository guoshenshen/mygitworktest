package com.elearning.service.TopicClassStudy;

import com.elearning.dao.TopicClassStudy.TopicBannerMapper;
import com.elearning.pojo.TopicClassStudy.TopicBanner;
import com.elearning.service.TopicClassStudy.ITopicBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("topicBannerService")
public class TopicBannerServiceImpl implements ITopicBannerService {

    @Autowired
    private TopicBannerMapper topicBannerMapper;

    public TopicBanner selectByPrimaryKey(Integer id){

        return topicBannerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TopicBanner> findListByMap(Map<String, Object> map) {
        return topicBannerMapper.findListByMap(map);
    }

    @Override
    public int update(TopicBanner banner) {
        return topicBannerMapper.updateByPrimaryKeySelective(banner);
    }

    @Override
    public int save(TopicBanner banner) {
        return topicBannerMapper.insertSelective(banner);
    }

    @Override
    public int delete(Integer bannerId) {
        return topicBannerMapper.deleteByPrimaryKey(bannerId);
    }

    @Override
    public List<TopicBanner> findOrderedBanner(Map<String, Object> topicBannerCondition) {
        return topicBannerMapper.findOrderedBanner(topicBannerCondition);
    }


}

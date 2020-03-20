package com.elearning.dao.recommend;

import com.elearning.pojo.recommend.ReceiverForRecommend;

public interface ReceiverForRecommendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReceiverForRecommend record);

    int insertSelective(ReceiverForRecommend record);

    ReceiverForRecommend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceiverForRecommend record);

    int updateByPrimaryKey(ReceiverForRecommend record);
}
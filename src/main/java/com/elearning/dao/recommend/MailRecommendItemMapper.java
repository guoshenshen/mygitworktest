package com.elearning.dao.recommend;

import com.elearning.pojo.recommend.MailRecommendItem;

public interface MailRecommendItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MailRecommendItem record);

    int insertSelective(MailRecommendItem record);

    MailRecommendItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MailRecommendItem record);

    int updateByPrimaryKey(MailRecommendItem record);
}
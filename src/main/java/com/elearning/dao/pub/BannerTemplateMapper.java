package com.elearning.dao.pub;

import com.elearning.pojo.pub.BannerTemplate;

public interface BannerTemplateMapper {
    int deleteByPrimaryKey(Integer banner_template_id);

    int insert(BannerTemplate record);

    int insertSelective(BannerTemplate record);

    BannerTemplate selectByPrimaryKey(Integer banner_template_id);

    int updateByPrimaryKeySelective(BannerTemplate record);

    int updateByPrimaryKey(BannerTemplate record);
}
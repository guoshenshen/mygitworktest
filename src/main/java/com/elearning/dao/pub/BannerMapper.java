package com.elearning.dao.pub;

import com.elearning.pojo.pub.Banner;

public interface BannerMapper {
    int deleteByPrimaryKey(Integer bannerId);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer bannerId);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);
}
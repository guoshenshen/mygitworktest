package com.elearning.dao.pub;

import com.elearning.pojo.pub.ResourceItem;

public interface ResourceItemMapper {
    int insert(ResourceItem record);

    int insertSelective(ResourceItem record);
}
package com.elearning.dao.visualize;

import com.elearning.pojo.visualize.GeoMap;

public interface GeoMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GeoMap record);

    int insertSelective(GeoMap record);

    GeoMap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GeoMap record);

    int updateByPrimaryKey(GeoMap record);
}
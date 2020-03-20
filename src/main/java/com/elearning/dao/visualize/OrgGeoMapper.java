package com.elearning.dao.visualize;

import com.elearning.pojo.visualize.OrgGeo;

public interface OrgGeoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrgGeo record);

    int insertSelective(OrgGeo record);

    OrgGeo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrgGeo record);

    int updateByPrimaryKey(OrgGeo record);
}
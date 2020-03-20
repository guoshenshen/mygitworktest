package com.elearning.dao.pub;

import com.elearning.pojo.pub.OrgXinxi;

public interface OrgXinxiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrgXinxi record);

    int insertSelective(OrgXinxi record);

    OrgXinxi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrgXinxi record);

    int updateByPrimaryKey(OrgXinxi record);
}
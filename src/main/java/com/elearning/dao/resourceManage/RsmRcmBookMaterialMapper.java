package com.elearning.dao.resourceManage;

import com.elearning.pojo.resourceManage.RsmRcmBookMaterial;

public interface RsmRcmBookMaterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RsmRcmBookMaterial record);

    int insertSelective(RsmRcmBookMaterial record);

    RsmRcmBookMaterial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RsmRcmBookMaterial record);

    int updateByPrimaryKey(RsmRcmBookMaterial record);
}
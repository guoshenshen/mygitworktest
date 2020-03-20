package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.PictureBase;

import java.util.List;
import java.util.Map;

public interface PictureBaseMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PictureBase record);

    int insertSelective(PictureBase record);

    PictureBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PictureBase record);

    int updateByPrimaryKey(PictureBase record);

    List<PictureBase> getListByParmMap(Map<String,Object> parmMap);

}
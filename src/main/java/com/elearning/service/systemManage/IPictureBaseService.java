package com.elearning.service.systemManage;

import com.elearning.pojo.systemManage.PictureBase;

import java.util.List;
import java.util.Map;


public interface IPictureBaseService {

    PictureBase findById(Integer id);

    List<PictureBase> getListByParmMap(Map<String,Object> parmMap);

    int deleteByPrimaryKey(Integer id);





}

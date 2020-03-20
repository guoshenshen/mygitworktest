package com.elearning.dao.plan;

import com.elearning.pojo.plan.TpTemplateWithBLOBs;

public interface TpTemplateMapper {
    int insert(TpTemplateWithBLOBs record);

    int insertSelective(TpTemplateWithBLOBs record);
}
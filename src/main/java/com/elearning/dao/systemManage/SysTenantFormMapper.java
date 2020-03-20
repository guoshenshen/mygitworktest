package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.SysTenantForm;
import com.elearning.pojo.systemManage.SysTenantFormKey;

public interface SysTenantFormMapper {
    int deleteByPrimaryKey(SysTenantFormKey key);

    int insert(SysTenantForm record);

    int insertSelective(SysTenantForm record);

    SysTenantForm selectByPrimaryKey(SysTenantFormKey key);

    int updateByPrimaryKeySelective(SysTenantForm record);

    int updateByPrimaryKey(SysTenantForm record);
}
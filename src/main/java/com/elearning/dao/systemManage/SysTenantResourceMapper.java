package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.SysTenantResource;
import com.elearning.pojo.systemManage.SysTenantResourceKey;

public interface SysTenantResourceMapper {
    int deleteByPrimaryKey(SysTenantResourceKey key);

    int insert(SysTenantResource record);

    int insertSelective(SysTenantResource record);

    SysTenantResource selectByPrimaryKey(SysTenantResourceKey key);

    int updateByPrimaryKeySelective(SysTenantResource record);

    int updateByPrimaryKey(SysTenantResource record);
}
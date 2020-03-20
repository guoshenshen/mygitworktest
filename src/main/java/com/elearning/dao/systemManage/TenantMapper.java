package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.Tenant;

import java.util.List;
import java.util.Map;

public interface TenantMapper {
    int deleteByPrimaryKey(Integer tenantId);

    int insert(Tenant record);

    int insertSelective(Tenant record);

    Tenant selectByPrimaryKey(Integer tenantId);

    int updateByPrimaryKeySelective(Tenant record);

    int updateByPrimaryKey(Tenant record);

    List<Tenant> listTenant();

    List<Tenant> findByCondition(Map<String, Object> conditions);


    List<Tenant> findByOrgId(int orgId);
}
package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.OrderTenant;

public interface OrderTenantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderTenant record);

    int insertSelective(OrderTenant record);

    OrderTenant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderTenant record);

    int updateByPrimaryKey(OrderTenant record);
}
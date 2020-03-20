package com.elearning.service.pub;


import com.elearning.pojo.pub.UserMenu;

import java.util.List;

public interface IUserMenuService {

    UserMenu selectByPrimaryKey(Integer id);

    boolean isHasMenuByOperatorIdAndTenantId(Integer operatorId,Integer tenantId);

    int insert(UserMenu record);

    List queryIconResourceList();
}

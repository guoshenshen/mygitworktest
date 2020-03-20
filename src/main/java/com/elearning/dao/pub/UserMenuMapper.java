package com.elearning.dao.pub;

import com.elearning.pojo.pub.UserMenu;

import java.util.List;
import java.util.Map;

public interface UserMenuMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserMenu record);

    int insertSelective(UserMenu record);

    UserMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMenu record);

    int updateByPrimaryKey(UserMenu record);

    boolean isHasMenuByOperatorIdAndTenantId(Integer operatorId,Integer tenantId);

    List<UserMenu> getUserMenuListByOperatorIdAndTeantId(Map<String,Object> parm);


}
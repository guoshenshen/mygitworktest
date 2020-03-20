package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.VUserRole;

public interface VUserRoleMapper {
    int insert(VUserRole record);

    int insertSelective(VUserRole record);
}
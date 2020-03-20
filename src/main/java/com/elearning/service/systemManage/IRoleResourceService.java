package com.elearning.service.systemManage;


import com.elearning.pojo.systemManage.RoleResource;

import java.util.List;
import java.util.Map;

public interface IRoleResourceService {

    RoleResource selectByPrimaryKey(Integer ID);

    public List<RoleResource> findByRoleId(Integer roleId);


}

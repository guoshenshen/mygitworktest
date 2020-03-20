package com.elearning.service.systemManage;


import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.systemManage.UserRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IUserRoleService {

    UserRole selectByPrimaryKey(Integer ID);

    List<UserRole> findByCondition(Map<String,Object> condition);

    HashMap queryUserRoleOrgId(Integer operatorId,Integer tenantId);

    String getUserRolesInString(Integer operatorId,Integer tenantId);

    HashMap queryUserRoleResourceList(Integer operatorId);

    HashMap queryUserRoleParentResourceList(HashMap map);

    HashMap queryRoleParentAndChildResourceList(HashMap map);

    HashMap queryOperatorResourceList(Integer operatorId);

    HashMap queryIconResourceList();

    /**
     * 创建学员角色
     * @return
     */
    public Map<String,Object> createStudentRole(Integer operatorID,Integer creatorId,Integer tenantId);







}

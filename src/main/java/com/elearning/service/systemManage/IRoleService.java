package com.elearning.service.systemManage;

import com.elearning.pojo.systemManage.Role;

import java.util.List;
import java.util.Map;


public interface IRoleService {

    public Role selectByPrimaryKey(Integer ID);

    public Role findRoleById(Integer ID);

    public String getRoleUrl(int roleId);

    public List<Role> findByCondition(Map<String, Object> conditionMap);

    public Integer addRole(Role role);

    public Boolean updateRole(Role role);

    public Map<String,Object> deleteById(Integer roleId);

    public Map<String,Object> deleteByIds(List<Integer> roleIdList);

}

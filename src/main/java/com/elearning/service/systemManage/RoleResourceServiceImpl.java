package com.elearning.service.systemManage;

import com.elearning.dao.systemManage.RoleResourceMapper;
import com.elearning.pojo.systemManage.RoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("roleResourceService")
public class RoleResourceServiceImpl implements IRoleResourceService{

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    public RoleResource selectByPrimaryKey(Integer ID){

        return roleResourceMapper.selectByPrimaryKey(ID);
    }

    @Override
    public List<RoleResource> findByRoleId(Integer roleId) {
        return roleResourceMapper.getRoleResourceListByRoleId(roleId);
    }


}

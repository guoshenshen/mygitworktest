package com.elearning.service.systemManage;

import com.elearning.dao.systemManage.SysTenantResourceMapper;
import com.elearning.pojo.systemManage.SysTenantResource;
import com.elearning.pojo.systemManage.SysTenantResourceKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysTenantResourceService")
public class SysTenantResourceServiceImpl implements ISysTenantResourceService {

    @Autowired
    private SysTenantResourceMapper sysTenantResourceMapper;

    public SysTenantResource selectByPrimaryKey(SysTenantResourceKey key){

        return sysTenantResourceMapper.selectByPrimaryKey(key);
    }


}

package com.elearning.service.systemManage;

import com.elearning.pojo.systemManage.SysTenantResource;
import com.elearning.pojo.systemManage.SysTenantResourceKey;

public interface ISysTenantResourceService {

    SysTenantResource selectByPrimaryKey(SysTenantResourceKey key);

}

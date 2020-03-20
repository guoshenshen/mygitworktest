package com.elearning.service.systemManage;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.systemManage.Tenant;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/6/24 10:02
 */
public interface ITenantService {

    Integer addTenant(Tenant tenant);

    Tenant findById(Integer tenantId);

    ServiceResponse all(Integer num,Integer size);

    List<Tenant> findAll();

    ServiceResponse findTenantName(HttpServletRequest request);

    ServiceResponse findTenantId(HttpServletRequest request);



}

package com.elearning.service.systemManage;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.dao.systemManage.TenantMapper;
import com.elearning.pojo.systemManage.Tenant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/6/24 10:03
 */
@Service("tenantService")
public class TenantServiceImpl implements ITenantService{

    @Autowired
    private TenantMapper tenantMapper;


    @Override
    public Integer addTenant(Tenant tenant) {
        return tenantMapper.insertSelective(tenant);
    }

    @Override
    public Tenant findById(Integer tenantId) {
        return tenantMapper.selectByPrimaryKey(tenantId);
    }

    @Override
    public ServiceResponse all(Integer num, Integer size) {
        PageHelper.startPage(num,size);
        List<Tenant> tenantList = tenantMapper.listTenant();
        PageInfo pageInfo = new PageInfo(tenantList);
        pageInfo.setList(tenantList);
        return ServiceResponse.createBySuccess(pageInfo);
    }

    @Override
    public List<Tenant> findAll() {
        return this.tenantMapper.listTenant();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse findTenantName(HttpServletRequest request){
        String result = Constants.tenantName;

        return ServiceResponse.createBySuccess(result);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse findTenantId(HttpServletRequest request){
        Integer result = Constants.tenantId;

        return ServiceResponse.createBySuccess(result);
    }


}

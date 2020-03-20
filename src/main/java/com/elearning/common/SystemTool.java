package com.elearning.common;

import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.util.PropertiesUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Component
public class SystemTool implements InitializingBean {

    public static Tenant currentTenant=null;

    private static ITenantService tenantService;

    public static ITenantService getTenantService() {
        return tenantService;
    }

    @Autowired
    public void setTenantService(ITenantService tenantService) {
        SystemTool.tenantService = tenantService;
    }

    public static String getRootUrlForRedirection(Tenant tenant){
        String openUrl="";
        if (tenant != null && tenant.getEnterUrl() != null
                && tenant.getEnterUrl().trim().length() > 0) {
            if (SystemTool.currentTenant.getIsRedirectToPortalWeb().equals(0)) {
                //当前租户平台具有驻留性
            }
            else{
                if(tenant.getWorkingStatus()==null||tenant.getWorkingStatus().equals((short)0)){
                    //用户隶属站点处于非正常工作状态
                }
                else{
                    if(!tenant.getTenantId().equals(SystemTool.currentTenant.getTenantId())){
                        openUrl = tenant.getEnterUrl().trim();
                    }
                }
            }
        }
        return openUrl;
    }

    public static String getRootUrlForRedirection(Integer tenantId){
        Tenant userTenant=SystemTool.tenantService.findById(tenantId);
        return SystemTool.getRootUrlForRedirection(userTenant);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SystemTool.currentTenant=this.tenantService.findById(Integer.parseInt(PropertiesUtil.getProperty("tenantId")));
    }
}

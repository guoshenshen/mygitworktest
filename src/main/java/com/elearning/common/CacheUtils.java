package com.elearning.common;

import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.systemManage.PageSetting;
import com.elearning.pojo.systemManage.Resource;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.coursemanage.ICourseTypeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.systemManage.IResourceService;
import com.elearning.service.systemManage.ITenantService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

@Service("cacheUtils")
public class CacheUtils implements InitializingBean{


    private static ITenantService tenantService;

    private static IResourceService resourceService;

    public static Map<Integer, Integer> PAGE_SETTING = new HashMap<Integer, Integer>();

    private static IEosorgTOrganizationService eosorgTOrganizationService;

    private static ICourseTypeService courseTypeService;

    @Autowired
    public CacheUtils(ITenantService tenantService,IEosorgTOrganizationService eosorgTOrganizationService,IResourceService resourceService){
        CacheUtils.tenantService=tenantService;
        CacheUtils.eosorgTOrganizationService=eosorgTOrganizationService;
        CacheUtils.resourceService=resourceService;
    }

    /**
     * 存储租户及租户名称的映射
     */
    private static Map<Integer,Tenant> tenantMapping=new HashMap<Integer,Tenant>();


    /**
     * 存储租户及租户org的映射
     */
    private static Map<Integer,EosorgTOrganization> tenantOrgMapping=new HashMap<Integer,EosorgTOrganization>();

    private static Map<Integer,String> courseTypeMapping = new HashMap<>();


    public static Tenant getTenant(Integer tenantId){
        if(tenantId==null){
            return null;
        }
        Tenant tenant=CacheUtils.tenantMapping.get(tenantId);
        if(tenant!=null){
            return tenant;
        }
        else{
            tenant=CacheUtils.tenantService.findById(tenantId);
            if(tenant!=null){
                CacheUtils.tenantMapping.put(tenantId, tenant);
            }
            return tenant;
        }
    }

    public static String getTenantName(Integer tenantId){
        String tenantName="";
        Tenant searchTenant=CacheUtils.getTenant(tenantId);
        if(searchTenant==null){
        } else{
            tenantName=searchTenant.getTenantName();
        }
        return tenantName;
    }

    public static String getTenantOrgSeq(Integer tenantId){
        if(tenantId==null){
            return null;
        }
        String orgseq=null;
        EosorgTOrganization org=CacheUtils.tenantOrgMapping.get(tenantId);
        if(org!=null){
            orgseq=org.getLogo();
        }
        else{
            EosorgTOrganization tenantOrg=CacheUtils.eosorgTOrganizationService.findByTenantId(tenantId);
            CacheUtils.tenantOrgMapping.put(tenantId, tenantOrg);
            orgseq=tenantOrg.getOrgSEQ();
        }
        return orgseq;
    }


    public static String getTenantLogo(Integer tenantId){
        if(tenantId==null){
            return null;
        }
        String logo=null;
        EosorgTOrganization org=CacheUtils.tenantOrgMapping.get(tenantId);
        if(org!=null){
            logo=org.getLogo();
        }
        else{
            EosorgTOrganization tenantOrg=CacheUtils.eosorgTOrganizationService.findByTenantId(tenantId);
            CacheUtils.tenantOrgMapping.put(tenantId, tenantOrg);
            logo=tenantOrg.getLogo();
        }
        return logo;
    }


    public static void initTenantMapping(){
        List<Tenant> tenantList=CacheUtils.tenantService.findAll();
        for(Tenant tenant:tenantList){
            CacheUtils.tenantMapping.put(tenant.getTenantId(), tenant);
        }
    }


    public static void updatePageSetting(PageSetting pageSetting) {
        CacheUtils.PAGE_SETTING.put(pageSetting.getOperatorID(),pageSetting.getPageSize());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initTenantMapping();

        initResourceMapping();
    }

    public static void initResourceMapping() {
        java.util.Timer timer = new java.util.Timer(true);
        TimerTask task = new TimerTask() {
            public void run() {
                Constants.resourceMap.clear();
                List<Resource> list = CacheUtils.resourceService.getAllResource();
                for (Resource res : list) {
                    Constants.resourceMap.put(res.getID().toString(), res);
                }
            }
        };
        timer.schedule(task, 0, 6 * 60 * 60 * 1000);
        return;
    }

}

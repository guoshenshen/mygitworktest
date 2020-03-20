package com.elearning.controller.systemManage;

import com.elearning.common.*;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.systemManage.*;
import com.elearning.service.systemManage.*;
import com.elearning.util.PropertiesUtil;
import com.elearning.util.ToolsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 */
@Controller
@RequestMapping("/findMenu/")
public class FindMenuController {

    @Autowired
    private IPageSettingService pageSettingService;

    @Autowired
    private IFindMenuService findMenuService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IRoleResourceService roleResourceService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private ISysTenantResourceService sysTenantResourceService;

    @RequestMapping("selectByPrimaryKey.do")
    @ResponseBody
    public ServiceResponse selectByPrimaryKey(Integer ID, Model model){
        UserResource userResource = findMenuService.selectByPrimaryKey(ID);

        return ServiceResponse.createBySuccess(userResource);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("updateIconList.do")
    @ResponseBody
    public ServiceResponse updateIconList(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        int operatorId=0;
        if(eosoperator!=null){
            operatorId=eosoperator.getOperatorId();
        }

        Map<String,Object> condition=new HashMap<>();
        String iconId = request.getParameter("iconid").toString();
        String flag = request.getParameter("flag").toString();

        condition.put("flag", flag);
        condition.put("iconId", iconId);
        condition.put("operatorId", operatorId);

        return  findMenuService.updateIconList(condition);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("changeRole.do")
    @ResponseBody
    public ServiceResponse changeRole(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(PropertiesUtil.getProperty("USERINFO_KEY"));
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        PageSetting pageSetting = this.pageSettingService.findByOperatorId(operator.getOperatorId());
        if(pageSetting == null){
            pageSetting = new PageSetting();
            pageSetting.setPageSize(20);
        }
        request.getSession().setAttribute("defaultPageSize", pageSetting.getPageSize());
        Integer operatorId = 0;
        if(operator != null){
            operatorId = operator.getOperatorId();
        }

        return findMenuService.changeRole(request,operatorId);

    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("setLocation.do")
    @ResponseBody
    public ServiceResponse setLocation(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        int operatorId=0;
        if(operator!=null){
            operatorId=operator.getOperatorId();
        }

        String parentId = request.getParameter("pId");
        String childId = request.getParameter("cId");

        Constants.locationMap.put(operatorId+"_"+"parentId",parentId);
        Constants.locationMap.put(operatorId+"_"+"childId",childId);

        List<Resource> temp = new ArrayList<>();
        if(parentId != null){
            temp.add(Constants.resourceMap.get(parentId));
        }
        if(childId != null){
            temp.add(Constants.resourceMap.get(childId));
        }
        request.getSession().setAttribute("BreadCrumb", temp);

        return ServiceResponse.createBySuccess();
    }


    @RequestMapping("getAdminMenu.do")
    @ResponseBody
    public ServiceResponse getAdminMenu(HttpServletRequest request){

        try{
            String sessionTagForRole=SessionTemp.CURRENT_ROLE.getName();
            String adminMenuTag=SessionTemp.ADMIN_MENU.getName();

            Integer currentRoleId=(Integer)request.getSession().getAttribute(sessionTagForRole);

            Map<Integer,List> menuStringMap=(Map<Integer, List>) request.getSession().getAttribute(adminMenuTag);

            if(menuStringMap==null){
                menuStringMap=new HashMap<>();
                request.getSession().setAttribute(adminMenuTag, menuStringMap);
            }
            return findMenuService.getAdminMenu(currentRoleId,menuStringMap);

        }catch(Exception e){
            e.printStackTrace();
            return ServiceResponse.createByError();
        }

    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("getTrace.do")
    @ResponseBody
    public ServiceResponse getTrace(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(PropertiesUtil.getProperty("USERINFO_KEY"));
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        String tag = SessionTemp.CURRENT_ROLE.getName();
        Integer currentRoleId=(Integer) request.getSession().getAttribute(tag);

        Role currentRole=null;

        if(currentRoleId!=null){
            currentRole=this.roleService.selectByPrimaryKey(currentRoleId);
        }
        List<Resource> breadcrumd=null;
        Map<String,Object> condition=new HashMap<>();

        try {
            breadcrumd = (List<Resource>) request.getSession().getAttribute("BreadCrumb");
            condition.put("role",currentRole);
            condition.put("trace",breadcrumd);
            return ServiceResponse.createBySuccess(condition);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResponse.createByError();
        }
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("getAllIcons.do")
    @ResponseBody
    public ServiceResponse getAllIcons(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        int operatorId=0;
        if(eosoperator!=null){
            operatorId=eosoperator.getOperatorId();
        }

        return  findMenuService.getAllIcons(operatorId);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("getSelectedIcon.do")
    @ResponseBody
    public ServiceResponse getSelectedIcon(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        int operatorId=0;
        if(eosoperator!=null){
            operatorId=eosoperator.getOperatorId();
        }

        return  findMenuService.getSelectedIcon(operatorId);
    }

    /**
     * 根据角色，初始化学员端菜单
     * @param request
     * @return
     */
    @RequestMapping("findStudentMenuForRole.do")
    @ResponseBody
    public ServiceResponse findStudentMenuForRole(HttpServletRequest request){

        //获取登录用户，获取登录用户角色，获得角色对应url，返回json

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Map<String,Object> resultMap = new HashMap<>();
        if(eosoperator != null) {
            //result.append("{\"name\":\""+((Eosoperator)request.getSession().getAttribute(Constants.USERINFO_KEY)).getOperatorName()+"\",");
            resultMap.put("name",eosoperator.getOperatorName());
            HashMap map = (HashMap) request.getSession().getAttribute(Constants.USERROLE_KEY);
            if(map.size()>0){
                Set set =map.keySet();
                Iterator it=set.iterator();
                boolean hasStudentRole = false;
                while(it.hasNext()){
                    Integer roleId= (Integer) it.next();
                    Integer orgId = (Integer)(map.get(roleId));
                    if(orgId==-1){
                        //学员
                        String stuUrl = this.roleService.getRoleUrl(roleId);
                        resultMap.put("stu",stuUrl);
                        hasStudentRole = true;
                    }
                    if(orgId.intValue()!=-1){
                        //管理员
                        String adminUrl = this.roleService.getRoleUrl(roleId);
                        resultMap.put("admin",adminUrl);
                        resultMap.put("adminRoleId",roleId);
                        if(hasStudentRole){
                            break;
                        }
                    }
                }
            }
        }else{
            //存放单点登录状态信息
            int tenantId=Integer.parseInt(PropertiesUtil.getProperty("tenant.tenantId").toString());
            Tenant tenant = this.tenantService.findById(tenantId);

            if(tenant != null){

                int isVirOrg = tenant.getIsVirOrg();
                Integer isSingleLogin = tenant.getIsSingleLogin();
                if(isSingleLogin==null){
                    int isSingleLoginDefault=Integer.parseInt(PropertiesUtil.getProperty("tenant.isSingleLogin").toString());
                    request.getSession().setAttribute("isSingleLogin", isSingleLoginDefault);//存放是否单点登录信息
                }else{
                    request.getSession().setAttribute("isSingleLogin", isSingleLogin);//存放是否单点登录信息
                }

                resultMap.put("isSingleLogin",isSingleLogin);//判断是否单点登录
                resultMap.put("isVirOrg",isVirOrg);//判断是否是虚拟机构
                resultMap.put("tenantId",tenantId);//判断是否是虚拟机构
            }
        }

        return  ServiceResponse.createBySuccess(resultMap);
    }

    /**
     * 初次获得单个角色的一级菜单
     * @param request
     * @return
     */
    @RequestMapping("getParentMenu.do")
    @ResponseBody
    public ServiceResponse getParentMenu(HttpServletRequest request){

        Map<String,Object> resutlMap = new HashMap<>();

        Integer currentRoleId=(Integer) request.getSession().getAttribute("userRoleId");
        List<Resource> singleRole_ParentResource=new ArrayList();
        HashMap menumap = Tools.getRoleresourcemap();
        if(menumap!=null && menumap.get(currentRoleId)!=null){
            singleRole_ParentResource.addAll((List<Resource>) menumap.get(currentRoleId));
            Collections.sort(singleRole_ParentResource, ToolsUtil.sortMenuSeq());
        }
        if(singleRole_ParentResource != null){
            for(Resource temp:singleRole_ParentResource){
                int parentId=temp.getID();
                String resourceName=temp.getResourceName();
                String resourceUrl=temp.getLink();
                Integer visible = 1;
                if(this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(parentId,Constants.tenantId,currentRoleId))!=null){
                    SysTenantResource sysTenantResource = this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(parentId,Constants.tenantId,currentRoleId));
                    resourceName = sysTenantResource.getCustomName();
                    visible = sysTenantResource.getVisible();
                }else 	if(this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(parentId,Constants.tenantId,0))!=null){
                    SysTenantResource sysTenantResource = this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(parentId,Constants.tenantId,0));
                    resourceName = sysTenantResource.getCustomName();
                    visible = sysTenantResource.getVisible();
                }
                //visible=1;
                if(visible==1){
                    resutlMap.put("_parentId",parentId);
                    resutlMap.put("resourceName",resourceName);
                    resutlMap.put("resourceUrl",resourceUrl);
                    resutlMap.put("currentSkinId",Constants.CURRENTSKINID);
                }
            }
        }

        return  ServiceResponse.createBySuccess(resutlMap);
    }

    /**
     * 获取用户的导航信息
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getLocation.do")
    @ResponseBody
    public ServiceResponse getLocation(HttpServletRequest request){

        /*Integer currentRoleId=(Integer) request.getSession().getAttribute("userRoleId");
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        int operatorId=0;
        if(eosoperator!=null){
            operatorId = eosoperator.getOperatorId();
        }
        String adminUrl="";
        if(currentRoleId!=null){
            adminUrl = roleService.getRoleUrl(currentRoleId);
        }
        int parentMenuId=-1;
        int childMenuId=-1;
        if(Constants.locationMap.get(operatorId+"_"+"parentId")!=null&&!Constants.locationMap.get(operatorId+"_"+"parentId").equals("")){
            parentMenuId=Integer.parseInt(Constants.locationMap.get(operatorId+"_"+"parentId").toString());
        }
        if(Constants.locationMap.get(operatorId+"_"+"childId")!=null&&!Constants.locationMap.get(operatorId+"_"+"childId").equals("")){
            childMenuId=Integer.parseInt(Constants.locationMap.get(operatorId+"_"+"childId").toString());
        }
        List<Resource> singleRole_ParentResource=new ArrayList();
        HashMap menumap=Tools.getRoleresourcemap();
        if(menumap!=null&&menumap.get(currentRoleId)!=null){
            singleRole_ParentResource.addAll((List<Resource>) menumap.get(currentRoleId));
        }
        HashMap resourcemap=Tools.getResourcerelationmap();
        List<Resource> singleRole_ChildMenu=new ArrayList();
        if(resourcemap!=null&&parentMenuId!=-1 && resourcemap.get(currentRoleId+"_"+parentMenuId)!=null){
            singleRole_ChildMenu.addAll((List<Resource>) resourcemap.get(currentRoleId+"_"+parentMenuId));
        }

        //String tmp="[";
        Map<String,Object> resutlMap = new HashMap<>();
        resutlMap.put("roleId",currentRoleId);
        resutlMap.put("adminUrl",adminUrl);
        if(singleRole_ParentResource!=null && singleRole_ParentResource.size()>0){
            for(Resource res : singleRole_ParentResource){
                if(res.getID().intValue() == parentMenuId){
                    String resourceName = res.getResourceName();
                    if(this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(parentMenuId,Constants.tenantId,currentRoleId))!=null){
                        SysTenantResource sysTenantResource = this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(parentMenuId,Constants.tenantId,currentRoleId));
                        resourceName = sysTenantResource.getCustomName();
                    }else if(this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(parentMenuId,Constants.tenantId,0)) != null){
                        SysTenantResource sysTenantResource = this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(parentMenuId,Constants.tenantId,0));
                        resourceName = sysTenantResource.getCustomName();
                    }

                    resutlMap.put("pId",res.getID());
                    resutlMap.put("pName",resourceName);
                    resutlMap.put("pUrl",res.getLink());

                    break;
                }

            }
        }
        if(singleRole_ChildMenu!=null&&singleRole_ChildMenu.size()>0){
            for(Resource resource:singleRole_ChildMenu){
                if(childMenuId!=0 && resource.getID().intValue()==childMenuId){
                    String resourceName = resource.getResourceName();
                    if(this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(childMenuId,Constants.tenantId,currentRoleId))!=null){
                        SysTenantResource sysTenantResource = this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(childMenuId,Constants.tenantId,currentRoleId));
                        resourceName = sysTenantResource.getCustomName();
                    }else 	if(this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(parentMenuId,Constants.tenantId,0))!=null){
                        SysTenantResource sysTenantResource = this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(childMenuId,Constants.tenantId,0));
                        if(sysTenantResource != null){
                            resourceName = sysTenantResource.getCustomName();
                        }
                    }
                    resutlMap.put("cId",resource.getID());
                    resutlMap.put("cName",resourceName);
                    resutlMap.put("cUrl",resource.getLink());
                    break;
                }else if(childMenuId==0){
                    String resourceName = resource.getResourceName();
                    if(this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(childMenuId,Constants.tenantId,currentRoleId))!=null){
                        SysTenantResource sysTenantResource = this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(childMenuId,Constants.tenantId,currentRoleId));
                        resourceName = sysTenantResource.getCustomName();
                    }else 	if(this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(childMenuId,Constants.tenantId,0))!=null){
                        SysTenantResource sysTenantResource = this.sysTenantResourceService.selectByPrimaryKey(new SysTenantResourceKey(childMenuId,Constants.tenantId,0));
                        resourceName = sysTenantResource.getCustomName();
                    }
                    resutlMap.put("cId",resource.getID());
                    resutlMap.put("cName",resourceName);
                    resutlMap.put("cUrl",resource.getLink());
                    break;
                }

            }
        }*/


        Integer currentRoleId=(Integer) request.getSession().getAttribute("userRoleId");
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        String adminUrl="";

        List<Map<String,Object>> resultList = new ArrayList<>();
        if(currentRoleId!=null){
            adminUrl = roleService.getRoleUrl(currentRoleId);
        }
        List<Resource> breadcrumd = (List<Resource>) request.getSession().getAttribute("BreadCrumb");
        Map<String,Object> resutlMap1 = new HashMap<>();
        Map<String,Object> resutlMap2 = new HashMap<>();
        Map<String,Object> resutlMap3 = new HashMap<>();

        if(breadcrumd != null && breadcrumd.size() >= 1 && breadcrumd.get(0) != null){
            if(breadcrumd.size() == 2 && breadcrumd.get(1) != null){
                resutlMap1.put("roleId",currentRoleId);
                resutlMap1.put("adminUrl",adminUrl);

                resutlMap2.put("pId",breadcrumd.get(0).getID());
                resutlMap2.put("pName",breadcrumd.get(0).getResourceName());
                resutlMap2.put("pUrl",breadcrumd.get(0).getLink());

                resutlMap3.put("cId",breadcrumd.get(1).getID());
                resutlMap3.put("cName",breadcrumd.get(1).getResourceName());
                resutlMap3.put("cUrl",breadcrumd.get(1).getLink());
            } else{
                resutlMap1.put("roleId",currentRoleId);
                resutlMap1.put("adminUrl",adminUrl);

                resutlMap2.put("pId",breadcrumd.get(0).getID());
                resutlMap2.put("pName",breadcrumd.get(0).getResourceName());
                resutlMap2.put("pUrl",breadcrumd.get(1).getLink());
            }
        } else{
            resutlMap1.put("roleId",currentRoleId);
            resutlMap1.put("adminUrl",adminUrl);

            resutlMap2.put("pId","");
            resutlMap2.put("pName","");
            resutlMap3.put("pUrl","");

            resutlMap3.put("cId","");
            resutlMap3.put("cName","");
            resutlMap3.put("cUrl","");
        }

        if(resutlMap1!=null){
            resultList.add(resutlMap1);
        }
        if(resutlMap1!=null){
            resultList.add(resutlMap2);
        }
        if(resutlMap1!=null){
            resultList.add(resutlMap3);
        }

        return  ServiceResponse.createBySuccess(resultList);
    }






}

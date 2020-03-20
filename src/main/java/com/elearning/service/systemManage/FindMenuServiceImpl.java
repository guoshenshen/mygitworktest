package com.elearning.service.systemManage;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.common.SessionTemp;
import com.elearning.dao.systemManage.UserResourceMapper;
import com.elearning.pojo.systemManage.PageSetting;
import com.elearning.pojo.systemManage.Resource;
import com.elearning.pojo.systemManage.Role;
import com.elearning.pojo.systemManage.UserResource;
import com.elearning.util.ToolsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service("findMenuService")
public class FindMenuServiceImpl implements IFindMenuService{

    @Autowired
    private UserResourceMapper userResourceMapper;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IPageSettingService pageSettingService;

    @Autowired
    private IRoleService roleService;

    public UserResource selectByPrimaryKey(Integer ID){

        return userResourceMapper.selectByPrimaryKey(ID);
    }

    public ServiceResponse updateIconList(Map<String,Object> map){

        int operatorId=Integer.parseInt(map.get("operatorId").toString());
        int iconResourceId=Integer.parseInt(map.get("iconId").toString());
        int selectFlag=Integer.parseInt(map.get("flag").toString());

        UserResource userResource = new UserResource();

        userResource.setOperatorId(operatorId);
        userResource.setResourceId(iconResourceId);
        userResource.setCreateTime(new Date());
        List<UserResource> userResourceList=userResourceMapper.findByOperatorId(operatorId);

        if(userResourceList!=null && userResourceList.size()>0){
            int count=0;
            for(UserResource userres:userResourceList){
                if(userres.getResourceId().intValue()==iconResourceId && selectFlag==0){
                    userResourceMapper.deleteByPrimaryKey(userres.getID());
                }else
                    count++;
            }
            if(userResourceList.size()==count && selectFlag==1){
                userResourceMapper.insert(userResource);
            }
        }else if(selectFlag==1){
            userResourceMapper.insert(userResource);
        }

        return ServiceResponse.createBySuccessMessage("修改成功");
    }

    public ServiceResponse getAllIcons(Integer operatorId){

        List<Resource> selectedIconList=new ArrayList();
        List<UserResource> userResourceList=userResourceMapper.findByOperatorId(operatorId);
        if(userResourceList!=null && userResourceList.size()>0){
            for(UserResource userres:userResourceList){
                selectedIconList.add(this.resourceService.selectByPrimaryKey(userres.getResourceId()));
            }
        }

        List<Resource> allIconList=new ArrayList<>();
        allIconList.addAll(this.resourceService.queryIconResourceList());
        List<Resource> uncheckedIconList=new ArrayList();

        if(selectedIconList!=null && selectedIconList.size()>0){
            for(int i=0;i<selectedIconList.size();i++){
                for(int j=0;j<allIconList.size();j++){
                    if(allIconList.get(j).getID().intValue()==selectedIconList.get(i).getID().intValue()){
                        allIconList.remove(j);
                        break;
                    }
                }
            }
        }
        uncheckedIconList.addAll(allIconList);
        int checkedflag=1;
        List<Map<String,Object>> returnResourceList=new ArrayList();

        if(selectedIconList!=null && selectedIconList.size()>0){
            for(Resource icon:selectedIconList){
                Map<String,Object> returnMap = new HashMap<>();
                returnMap.put("count",checkedflag);
                returnMap.put("iconId",icon.getID());
                returnMap.put("iconName",icon.getResourceName());
                returnMap.put("iconUrl",icon.getLinkBase());

                //gss--对接基地网站时，图片链接前有“点”，"./image/pxrc.png",基地网不需要此"点"，
                //returnMap.put("iconSrc",icon.getIconDir());
                returnMap.put("iconSrc",icon.getIconDirBase());

                returnResourceList.add(returnMap);
            }
        }
        if(uncheckedIconList!=null&&uncheckedIconList.size()>0){
            for(Resource icon:uncheckedIconList){
                Map<String,Object> returnMap = new HashMap<>();
                returnMap.put("count",checkedflag-1);
                returnMap.put("iconId",icon.getID());
                returnMap.put("iconName",icon.getResourceName());
                returnMap.put("iconUrl",icon.getLinkBase());

                returnMap.put("iconSrc",icon.getIconDirBase());
                returnResourceList.add(returnMap);
            }
        }
        return ServiceResponse.createBySuccess(returnResourceList);
    }

    public ServiceResponse getSelectedIcon(Integer operatorId){

        List<Resource> selectedIconList=new ArrayList();
        List<UserResource> userResourceList=userResourceMapper.findByOperatorId(operatorId);
        if(userResourceList!=null && userResourceList.size()>0){
            for(UserResource userres:userResourceList){
                selectedIconList.add(this.resourceService.selectByPrimaryKey(userres.getResourceId()));
            }
        }

        List<Map<String,Object>> returnResourceList=new ArrayList();

        if(selectedIconList!=null && selectedIconList.size()>0){
            Collections.sort(selectedIconList, ToolsUtil.sortMenuSeq());
            for(Resource icon:selectedIconList){
                Map<String,Object> returnMap = new HashMap<>();
                returnMap.put("iconSeq",icon.getSeq());
                returnMap.put("iconId",icon.getID());
                returnMap.put("iconName",icon.getResourceName());
                returnMap.put("iconUrl",icon.getLinkBase());
                returnMap.put("iconSrc",icon.getIconDirBase());
                returnResourceList.add(returnMap);
            }
        }

        return ServiceResponse.createBySuccess(returnResourceList);
    }

    public ServiceResponse getAdminMenu(Integer currentRoleId,Map<Integer,List> menuStringMap){

        if(menuStringMap.containsKey(currentRoleId)){

        } else {
            //Map<Integer,List<Resource>> resourceTree=this.getRoleResourceService().findResourceTreeByRoleId(currentRoleId);

            //----------------------------------------------------
            Map<Integer,List<Resource>> result=new HashMap<Integer,List<Resource>>();
            //===================
            //List<Resource> resourceList=this.findResourceByRoleId(roleId);
            Map<String, Object> condition = new HashMap<>();
            condition.put("role_id", currentRoleId);
            List<Resource> resourceList = this.resourceService.getResourceListByRoleId(condition);
            //===================

            if(resourceList!=null && resourceList.size()>0){
                result.put(-1, resourceList);
                Map<String,Object> condition2=new HashMap<String,Object>();
                condition2.put("status", 1);
                for(Resource resource:resourceList){
                    condition2.put("parentId", resource.getID());
                    condition2.put("roleId", currentRoleId);
                    List<Resource> subResourceList = this.resourceService.getResourceListByConditionSQL(condition2);
                    if(subResourceList!=null && subResourceList.size()>0){
                        result.put(resource.getID(), subResourceList);
                    }
                }
            }
            //----------------------------------------------------

            Set<Integer> keySet=result.keySet();
            Map<Integer,Object> resultMap = new HashMap<>();
            for(Integer key : keySet){
                List<Resource> resourceListWithSameParent=result.get(key);
                menuStringMap.put(key,resourceListWithSameParent);
            }
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("menuInfo",menuStringMap);

        return ServiceResponse.createBySuccess(resultMap);

    }


    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse changeRole(HttpServletRequest request,Integer operatorId){
        try{
            PageSetting pageSetting = this.pageSettingService.selectByPrimaryKey(operatorId);
            if(pageSetting == null){
                pageSetting = new PageSetting();
                pageSetting.setPageSize(20);
            }
            request.getSession().setAttribute("defaultPageSize", pageSetting.getPageSize());
            String _roleId = request.getParameter("roleId");
            int roleId = Integer.parseInt(_roleId);

            Role role = this.roleService.selectByPrimaryKey(roleId);

            Integer resourceId = role.getDefaultResourceId();
            Resource defaultResource = this.resourceService.selectByPrimaryKey(resourceId);

            this.setLocation(request,defaultResource);
            String targetUrl = defaultResource.getHyperlink();

            //=================================================================================
            request.getSession().setAttribute(SessionTemp.CURRENT_ROLE.getName(), roleId);

            Map map = (Map) request.getSession().getAttribute(Constants.USERROLE_KEY);
            Integer orgId = -1;

            if(map.get(roleId) != null){
                orgId = Integer.parseInt(map.get(roleId).toString());
            }
            if(orgId != -1){
                request.getSession().setAttribute(Constants.ROOTORGID_KEY, orgId);
                request.getSession().setAttribute("orgId", orgId);//修复切换角色后，查看机构信息bug
            }
            //=================================================================================
            Map<String,Object> returnMap = new HashMap<>();
            returnMap.put("hyperlink",targetUrl);
            return ServiceResponse.createBySuccess(returnMap);

        }catch (Exception e){
            e.printStackTrace();
            return ServiceResponse.createByErrorMessage("角色切换失败！");
        }
    }

    private void setLocation(HttpServletRequest request,Resource resource){
        Resource parentResource=resource.getParent();
        List<Resource> temp = new ArrayList<>();
        if(parentResource!=null){
            temp.add(Constants.resourceMap.get(parentResource.getID().toString()));
        }
        temp.add(Constants.resourceMap.get(resource.getID().toString()));
        request.getSession().setAttribute("BreadCrumb", temp);
    }

}

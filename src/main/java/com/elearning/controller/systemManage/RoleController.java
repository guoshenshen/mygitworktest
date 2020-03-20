package com.elearning.controller.systemManage;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.systemManage.Role;
import com.elearning.pojo.systemManage.RoleResource;
import com.elearning.pojo.systemManage.UserRole;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.systemManage.IRoleResourceService;
import com.elearning.service.systemManage.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/role/")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRoleResourceService roleResourceService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;


    /**
     * 根据ID查询某一个ID角色
     * @param ID
     * @param model
     * @return
     */
    @RequestMapping("getRoleByID.do")
    @ResponseBody
    public ServiceResponse getRoleByID(Integer ID, Model model){

        Role role = roleService.selectByPrimaryKey(ID);

        return ServiceResponse.createBySuccess(role);
    }

    /**
     * 进入角色编辑页面（新建/修改）
     * @param roleId
     * @param request
     * @param session
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "forAddRole.do")
    public String forAddRole(Integer roleId,HttpServletRequest request,HttpSession session){
        Role roleForm = null;
        if(roleId!=null){
            //修改角色
            roleForm=this.roleService.selectByPrimaryKey(roleId);
        }
        else{
            //新建角色
            roleForm=new Role();
        }
        Integer orgId = Integer.valueOf(request.getSession()
                .getAttribute(Constants.ROOTORGID_KEY).toString());
        EosorgTOrganization organization = this.eosorgTOrganizationService
                .findById(orgId);
        request.setAttribute("isVirOrg", organization.getIsVirOrg());
        if(roleForm.getIsVirRole()==null){
            roleForm.setIsVirRole(organization.getIsVirOrg());
        }
        request.setAttribute("role", roleForm);   ;
        return "system/updateRole";
    }

    /**
     * 角色信息保存/更新
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "addRole.do")
    @ResponseBody
    public ServiceResponse addRole(Role roleForm,HttpSession session){
        Boolean status = false;
        StringBuffer cause = new StringBuffer("");
        String newRoleDefaultUrl="default.jsp";
        EosOperator login_user = ( EosOperator)session.getAttribute(Constants.USERINFO_KEY);
        Integer login_user_id= login_user_id = login_user.getOperatorId();
        //判断角色编号是否与系统已有编号重复
        Map<String,Object> condition=new HashMap<String,Object>();
        List<Role> roleList=this.roleService.findByCondition(condition);
        Boolean flag=false;
        if(roleList!=null&&roleList.size()>0){
            for(Role _role:roleList) {
                if (!_role.getID().equals(roleForm.getID())) {
                    flag = true;
                    break;
                }
            }
        }
        if(flag == true){
            return ServiceResponse.createByErrorMessage("角色编号与系统中已有编号重复，无法保存，请修改");
        }
        else{
            roleForm.setCreatorId(login_user_id);
            roleForm.setRoleUrl(newRoleDefaultUrl);
            Integer orgId = Integer.valueOf(session.getAttribute(Constants.ROOTORGID_KEY).toString());
            roleForm.setParentOrgId(orgId);
            if(roleForm.getID()==null){
                this.roleService.addRole(roleForm);
                if(roleForm.getID()!=null){
                    status=true;
                }
            }
            else{
                status=this.roleService.updateRole(roleForm);
            }
            if(status){
                return ServiceResponse.createBySuccess("用户角色编辑成功",roleForm);
            }
            else{
                return ServiceResponse.createByErrorMessage("用户角色编辑失败");
            }
        }
    }


    /*public ServiceResponse deleteRole(HttpServletRequest request){
        if(request.getParameterValues("roleId") != null) {
            String[] delete_roleIds = request.getParameterValues("roleId");
            RoleResource roleResourceExample=new RoleResource();
            UserRole userRoleExample=new UserRole();
            for(String delete_roleId: delete_roleIds){
                try{
                    List<RoleResource> role_resources=this.roleResourceService.findByRoleId(Integer.parseInt(delete_roleId));
                    for(RoleResource role_resource: role_resources){
                        this.delete(role_resource);
                    }
                }catch(Exception e){

                }

            }
            String message = this.roleService.deleteById(delete_roleIds);
            if(message.length() != 0){
                boolean status = false;
                result.append("{\"status\":").append(status).append(",")
                        .append("\"course\":").append("\"").append(message).append("\"").append("}");;
            }
            else{
                boolean status = true;
                result.append("{\"status\":").append(status).append(",")
                        .append("\"course\":").append("\"\"").append("}");;
            }
        }

    }*/

    @RequestMapping("listRoles.do")
    @ResponseBody
    public ServiceResponse listRoles(HttpServletRequest request,String roleName,@RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                     @RequestParam(value = "count",defaultValue = "10")Integer count, HttpSession session){
        EosOperator operator = (EosOperator) request.getSession().getAttribute(
                Constants.USERINFO_KEY);
        Integer operatorId =  operator.getOperatorId();
        request.setAttribute("login_user",operator);
        Integer orgId =Integer.valueOf(session.getAttribute(Constants.ROOTORGID_KEY).toString());
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("operatorId", operatorId);
        if(roleName!=null&&roleName.trim().length()>0){
            conditionMap.put("roleName","%" + roleName.trim() + "%");
        }
        try {
            PageHelper.startPage(startIndex,count);
            List<Role> userRoleList = this.roleService.findByCondition(conditionMap);
            PageInfo pageInfo = new PageInfo(userRoleList);
            pageInfo.setList(userRoleList);
            return ServiceResponse.createBySuccess(pageInfo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ServiceResponse.createByErrorMessage("角色信息获取事变");
        }
    }


    @RequestMapping("listRole.do")
    @ResponseBody
    public ServiceResponse listRole(HttpServletRequest request){

        String roleIdString=((EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY))==null?"":((EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY)).getUser_idcode();

        List<Map<String,Object>> returnList = new ArrayList<>();

        if(!roleIdString.equals("")){
            String[] roleIdArr=roleIdString.split(";");
            String currentRoleName="";
            Integer currentRoleId=(Integer) request.getSession().getAttribute("userRoleId");
            currentRoleName=this.roleService.findRoleById(currentRoleId.intValue()).getRoleName();
            int count=0;
            Map<String,Object> result1 = new HashMap<>();

            result1.put("count",count);
            result1.put("currentRoleName",currentRoleName);
            returnList.add(result1);

            for(int i=0;i<roleIdArr.length;i++){
                count++;
                int roleId=Integer.parseInt(roleIdArr[i]);
                Role role = this.roleService.selectByPrimaryKey(roleId);

                Map<String,Object> result2 = new HashMap<>();

                result2.put("count",count);
                result2.put("roleId",roleId);
                result2.put("roleName",role.getRoleName());
                result2.put("roleUrl",role.getRoleUrl());

                returnList.add(result2);

            }

        }

        return ServiceResponse.createBySuccess(returnList);

    }















}

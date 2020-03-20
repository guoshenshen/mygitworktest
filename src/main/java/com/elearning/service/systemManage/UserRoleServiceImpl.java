package com.elearning.service.systemManage;

import com.elearning.dao.pub.EosOperatorMapper;
import com.elearning.dao.pub.EosorgTEmployeeMapper;
import com.elearning.dao.pub.EosorgTOrganizationMapper;
import com.elearning.dao.pub.UserVOrganizationMapper;
import com.elearning.dao.systemManage.*;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.pub.UserVOrganization;
import com.elearning.pojo.systemManage.*;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.util.PropertiesUtil;
import com.elearning.util.ToolsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userRoleService")
public class UserRoleServiceImpl implements IUserRoleService{

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private EosOperatorMapper eosOperatorMapper;

    @Autowired
    private EosorgTEmployeeMapper eosorgTEmployeeMapper;

    @Autowired
    private EosorgTOrganizationMapper eosorgTOrganizationMapper;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService ;

    @Autowired
    private TenantMapper tenantMapper ;

    @Autowired
    private UserVOrganizationMapper userVOrganizationMapper ;

    @Autowired
    private RoleResourceMapper roleResourceMapper ;

    @Autowired
    private ResourceMapper resourceMapper ; 


    public UserRole selectByPrimaryKey(Integer ID){

        return userRoleMapper.selectByPrimaryKey(ID);
    }

    @Override
    public List<UserRole> findByCondition(Map<String, Object> condition) {
        return userRoleMapper.findByCondition(condition);
    }

    public HashMap queryUserRoleOrgId(Integer operatorId,Integer tenantId){

        HashMap map = new HashMap();

        Map<String,Object> parm = new HashMap<>();
        parm.put("user_id",operatorId);
        parm.put("tenantId",tenantId);

        List<UserRole> userRoleList = this.userRoleMapper.getUserRoleListByUserID(parm);

        for(UserRole userRole:userRoleList){
            Role role = this.roleMapper.selectByPrimaryKey(userRole.getRoleID());
            Integer orgId = 0;
            EosOperator eosOperator = this.eosOperatorMapper.selectByPrimaryKey(operatorId);
            if(role.getIsVirRole().intValue()==0 && eosOperator.getStatus()!=null && eosOperator.getStatus()==1){
                if(role.getOrgId().intValue()==0){
                    EosorgTEmployee employee=this.eosorgTEmployeeMapper.selectByPrimaryKey(operatorId);
                    if(employee!=null){
                        orgId = this.eosorgTOrganizationService.findOrgId(employee.getOrgID());
                    }
                    if(role.getID()!=null && role.getID().equals(66)){
                        //系统默认设置的所级管理员
                        Tenant tenant=this.tenantMapper.selectByPrimaryKey(tenantId);
                        map.put(role.getID(), tenant.getOrgId());
                    } else{
                        map.put(role.getID(), orgId);
                    }
                } else{
                    map.put(role.getID(), role.getOrgId());
                }
            }else if(role.getIsVirRole().intValue()==1){
                Integer deptId = 0;
                if(role.getOrgId()!=null&&role.getOrgId().intValue()!=0){
                    deptId = role.getOrgId();
                } else {
                    //deptId = this.userVorganizationService.findVorgId(operatorId,role.getParentOrgId());

                    List<UserVOrganization> userVOrganizationList = this.userVOrganizationMapper.getUserVorganizationListByOperatorId(operatorId);
                    for(UserVOrganization userVOrganization : userVOrganizationList){
                        String vOrgSeq = this.eosorgTOrganizationMapper.selectByPrimaryKey(userVOrganization.getOrgID()).getOrgSEQ();
                        if(vOrgSeq.indexOf(String.valueOf(role.getParentOrgId()))>-1){
                            deptId = userVOrganization.getOrgID();
                        }
                    }
                }

                UserVOrganization userVOrg = new UserVOrganization();
                userVOrg.setOperatorID(operatorId);
                userVOrg.setOrgID(deptId);
                UserVOrganization userVOrganization = this.userVOrganizationMapper.selectByPrimaryKey(userVOrg);
                if(userVOrganization!=null && userVOrganization.getStatus()==1){
                    orgId = this.eosorgTOrganizationService.findOrgId(deptId);
                    map.put(role.getID(), orgId);
                }
            }
        }
        return map;
    }



    public String getUserRolesInString(Integer operatorId,Integer tenantId){

        //通过operatorId查询出该用户的所有角色
        //List<UserRole> userRoleList = this.getUserRoleDAO().findByOperatorId(operatorId);
        List<UserRole> userRoleList = this.userRoleMapper.getUserRoleListByUser_id(operatorId);

        StringBuffer userRoleStringBuffer = new StringBuffer();
        for(UserRole userRole:userRoleList){
            if(userRole.getTenantId()==null || userRole.getTenantId().intValue() == tenantId.intValue()){
                Integer currentTenantId=Integer.parseInt(PropertiesUtil.getProperty("tenantId"));
                if(!(currentTenantId == 1005 && userRole.getRoleID()==1)){
                    userRoleStringBuffer.append(userRole.getRoleID()).append(";");
                }
            }
        }
        return userRoleStringBuffer.toString();

    }

    public HashMap queryUserRoleResourceList(Integer operatorId){

        HashMap menumap=new HashMap();
        List<UserRole> userRoleList = this.userRoleMapper.getUserRoleListByUser_id(operatorId);
        for(UserRole userrole:userRoleList){
            int roleId=userrole.getRoleID();
            //List<RoleResource> roleResourceList=this.getRoleResourceService().findByRoleId(roleId);
            List<RoleResource> roleResourceList=this.roleResourceMapper.getRoleResourceListByRoleId(roleId);
            List<Resource> role_ResourceList=new ArrayList();
            for(RoleResource roleResource:roleResourceList){
                Resource single_resource=this.resourceMapper.selectByPrimaryKey(roleResource.getResource_ID());
                if(single_resource!=null && single_resource.getStatus()==1){
                    role_ResourceList.add(single_resource);
                }
            }
            Collections.sort(role_ResourceList, ToolsUtil.sortMenuSeq());
            menumap.put(roleId, role_ResourceList);
        }
        return menumap;

    }

    public HashMap queryUserRoleParentResourceList(HashMap map){

        HashMap parentmap=new HashMap();
        if(map.size()>0){
            Set set=map.keySet();
            Iterator itr=set.iterator();
            while(itr.hasNext()){
                Integer roleId=(Integer) itr.next();
                List<Resource> resourceList=(List<Resource>) map.get(roleId.intValue());
                List<Resource> singleRole_parentMenuList=new ArrayList();
                for(Resource resource:resourceList){
                    if(resource!=null){
                        Object obj=resource.getParent();
                        if(obj==null||obj.equals("")){
                            singleRole_parentMenuList.add(resource);
                        }
                    }
                }
                Collections.sort(singleRole_parentMenuList,ToolsUtil.sortMenuSeq());
                parentmap.put(roleId, singleRole_parentMenuList);
            }
        }
        return parentmap;

    }

    public HashMap queryRoleParentAndChildResourceList(HashMap map){

        HashMap resourcemap = new HashMap();
        if (map.size() > 0) {
            Set set = map.keySet();
            Iterator itr = set.iterator();
            while (itr.hasNext()) {
                Integer roleId = (Integer) itr.next();
                List<Resource> resourceList = (List<Resource>) map.get(roleId.intValue());
                Resource _resource = new Resource();
                int parentId = 0;
                for (Resource resource : resourceList) {
                    List<Resource> singleRole_childMenuList = new ArrayList();
                    if (resource != null) {
                        Object obj = resource.getParent();
                        if (obj == null || obj.equals(null)) {
                            parentId = resource.getID();
                            Set<Resource> childSet = resource.getChildrenResources();
                            Iterator iter = childSet.iterator();
                            while (iter.hasNext()) {
                                _resource = (Resource) iter.next();
                                for (Resource tempresource : resourceList)
                                    if (tempresource.getID().intValue() == _resource.getID().intValue()) {
                                        singleRole_childMenuList.add(_resource);
                                    }
                            }
                            Collections.sort(singleRole_childMenuList,
                                    ToolsUtil.sortMenuSeq());
                            resourcemap.put(roleId + "_" + parentId,
                                    singleRole_childMenuList);
                        }
                    }
                }
            }
        }
        return resourcemap;

    }

    @Override
    public HashMap queryOperatorResourceList(Integer operatorId) {
        return null;
    }

    @Override
    public HashMap queryIconResourceList() {
        return null;
    }

    @Override
    public Map<String, Object> createStudentRole(Integer operatorID,Integer creatorId,Integer tenantId) {
        Map<String,Object> result=new HashMap<String,Object>();
        Boolean status=null;
        try {
            UserRole student = new UserRole();
            student.setRoleID(1);
            student.setUserID(operatorID);
            student.setCreatorID(creatorId);
            student.setCreateTime(new Date());
            this.userRoleMapper.insert(student);
            status=true;
            result.put("roleId",student.getID());
        } catch (Exception e) {
            status=false;
            result.put("msg","学员角色添加失败");
            e.printStackTrace();
        } finally {
            result.put("status",status);
            return result;
        }
    }


}

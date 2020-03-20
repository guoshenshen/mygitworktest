package com.elearning.service.systemManage;

import com.elearning.dao.systemManage.RoleMapper;
import com.elearning.pojo.systemManage.Role;
import com.elearning.pojo.systemManage.UserRole;
import com.elearning.service.systemManage.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("roleService")
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private IUserRoleService userRoleService;

    public Role selectByPrimaryKey(Integer ID){

        return roleMapper.selectByPrimaryKey(ID);
    }

    public Role findRoleById(Integer ID){
        return roleMapper.selectByPrimaryKey(ID);
    }

    public String getRoleUrl(int roleId){
        return roleMapper.selectByPrimaryKey(roleId).getRoleUrl();
    }

    @Override
    public List<Role> findByCondition(Map<String, Object> conditionMap) {
        return roleMapper.findByCondition(conditionMap);
    }

    @Override
    public Integer addRole(Role role) {
        if(role.getOrgId()==null||role.getOrgId().toString().trim().equals("")){
            role.setOrgId(0);
            role.setOrgsname("管理员所属单位");
            role.setCreateTime(new Date());
        }else{
            role.setOrgId(Integer.valueOf(role.getOrgId()));
            role.setOrgsname(role.getOrgsname());
        }
        Integer count=this.roleMapper.insert(role);
        if(count>0){
            return role.getID();
        }
        else{
            return null;
        }
    }

    @Override
    public Boolean updateRole(Role role) {
        if(role.getOrgId()==null||role.getOrgId().toString().trim().equals("")){
            role.setOrgId(0);
            role.setOrgsname("管理员所属单位");
        }else{
            role.setOrgId(Integer.valueOf(role.getOrgId()));
            role.setOrgsname(role.getOrgsname());
        }
        Integer count=this.roleMapper.updateByPrimaryKey(role);
        if(count>0){
            return true;
        }
        else{
            return false;
        }
    }


    public Map<String,Object> deleteRole(Role role){
        Map<String,Object> resultInfo=new HashMap<String,Object>();
        Map<String,Object> condition=new HashMap<String,Object>();
        condition.put("roleId",role.getID());
        List<UserRole> urList=this.userRoleService.findByCondition(condition);
        if(urList!=null&&urList.size()>0){
            resultInfo.put("status",false);
            resultInfo.put("msg","无法删除角色"+role.getRoleName()+",请先将角色权限从角色分配中收回");
        }
        Integer count=this.roleMapper.deleteByPrimaryKey(role.getID());
        if(count>0){
            resultInfo.put("status",true);
        }
        else{
            resultInfo.put("status",true);
            resultInfo.put("msg","角色"+role.getRoleName()+"删除失败");
        }
        return resultInfo;
    }

    @Override
    public Map<String,Object> deleteById(Integer roleId) {
        Role role=this.findRoleById(roleId);
        Map<String,Object> resultInfo=null;
        if(role!=null){
            resultInfo=this.deleteRole(role);
        }
        else{
            resultInfo=new HashMap<String,Object>();
            resultInfo.put("status",false);
            resultInfo.put("msg","角色信息删除失败");
        }
        return resultInfo;
    }

    @Override
    public Map<String,Object> deleteByIds(List<Integer> roleIdList) {
        Map<String,Object> resultInfo=new HashMap<String,Object>();
        List<String> causes=new ArrayList<String>();
        resultInfo.put("msg",causes);
        Boolean status=true;
        for(Integer roleId:roleIdList){
            Map<String,Object> partResult=new HashMap<String,Object>();
            if(!Boolean.valueOf(partResult.get("status").toString())){
                status=false;
                causes.add(partResult.get("msg").toString());
            }
        }
        resultInfo.put("status",status);
        return  resultInfo;
    }


}

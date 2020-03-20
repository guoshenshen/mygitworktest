package com.elearning.service.pub;

import com.elearning.dao.pub.LoginAuthMapper;
import com.elearning.dao.pub.UserMenuMapper;
import com.elearning.pojo.pub.LoginAuth;
import com.elearning.pojo.pub.UserMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userMenuService")
public class UserMenuServiceImpl implements IUserMenuService {

    @Autowired
    private UserMenuMapper userMenuMapper;

    @Override
    public UserMenu selectByPrimaryKey(Integer id){

        return userMenuMapper.selectByPrimaryKey(id);
    }

    public boolean isHasMenuByOperatorIdAndTenantId(Integer operatorId,Integer tenantId){

        Map<String,Object> parm = new HashMap<>();
        parm.put("operatorId",operatorId);
        parm.put("tenantId",tenantId);

        List<UserMenu> userMenuList = this.userMenuMapper.getUserMenuListByOperatorIdAndTeantId(parm);

        if(userMenuList!=null && userMenuList.size()>0){
            return false;
        } else{
            return true;
        }

    }

    public int insert(UserMenu record){
        Long dynamicKey=new Long(new Date().getTime());
        Integer id=Math.abs(dynamicKey.intValue());
        record.setId(id);
        return userMenuMapper.insert(record);
    }

    @Override
    public List queryIconResourceList() {
        //todo
        return null;
    }

}

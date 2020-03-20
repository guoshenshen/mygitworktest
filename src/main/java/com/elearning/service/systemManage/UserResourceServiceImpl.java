package com.elearning.service.systemManage;

import com.elearning.common.Tools;
import com.elearning.dao.systemManage.ResourceMapper;
import com.elearning.dao.systemManage.UserResourceMapper;
import com.elearning.pojo.systemManage.Resource;
import com.elearning.pojo.systemManage.UserResource;
import com.elearning.util.ToolsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service("userResourceService")
public class UserResourceServiceImpl implements IUserResourceService{

    @Autowired
    private UserResourceMapper userResourceMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    public UserResource selectByPrimaryKey(Integer ID){

        return userResourceMapper.selectByPrimaryKey(ID);
    }

    public HashMap queryOperatorResourceList(Integer operatorId){

        HashMap iconmap=new HashMap();
        List<Resource> iconResourceList=new ArrayList();
        List<UserResource> userResourceList=this.userResourceMapper.findByOperatorId(operatorId);
        if(userResourceList!=null&&userResourceList.size()>0){
            for(UserResource userresource:userResourceList){
                iconResourceList.add(this.resourceMapper.selectByPrimaryKey(userresource.getResourceId()));
            }
            Collections.sort(iconResourceList, ToolsUtil.sortMenuSeq());
            iconmap.put(operatorId, iconResourceList);
        }
        return iconmap;
    }

    public List queryIconResourceList(){
        List<Resource> iconResourceList=new ArrayList<Resource>();
        List<Resource> allResourceList=this.resourceMapper.findAll();
        for(Resource res : allResourceList){    //3004:培训实施
            if(res.getParent()!=null&&res.getParent().getID().intValue()==3004 && res.getStatus().intValue()==1){
                iconResourceList.add(res);
            }
        }
        Collections.sort(iconResourceList,ToolsUtil.sortMenuSeq());
        return iconResourceList;

    }


}

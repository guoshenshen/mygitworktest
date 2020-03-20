package com.elearning.service.systemManage;

import com.elearning.dao.systemManage.ResourceMapper;
import com.elearning.pojo.systemManage.Resource;
import com.elearning.util.ToolsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/6/25 11:20
 */

@Service("resourceService")
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    public Resource selectByPrimaryKey(Integer ID) {
        return resourceMapper.selectByPrimaryKey(ID);
    }

    public List<Resource> getResourceListByRoleId(Map<String,Object> condition){

        return resourceMapper.getResourceListByRoleId(condition);
    }

    public List<Resource> getResourceListByConditionSQL(Map<String,Object> condition){

        return resourceMapper.getResourceListByConditionSQL(condition);
    }

    public List<Resource> queryIconResourceList(){

        List<Resource> iconResourceList=new ArrayList<>();
        List<Resource> allResourceList=this.resourceMapper.findAll();

        for(Resource res:allResourceList){    //3004:培训实施
            if(res.getParentId()!=null && res.getParentId()==3004 && res.getStatus()==1){//res.getParent()!=null &&
                iconResourceList.add(res);
            }
        }
        Collections.sort(iconResourceList, ToolsUtil.sortMenuSeq());
        return iconResourceList;
    }

    public List<Resource> getAllResource(){

        return this.resourceMapper.getAllResource();
    }



}

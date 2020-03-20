package com.elearning.service.systemManage;

import com.elearning.dao.systemManage.PageSettingMapper;
import com.elearning.dao.systemManage.UserRoleMapper;
import com.elearning.pojo.systemManage.PageSetting;
import com.elearning.pojo.systemManage.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pageSettingService")
public class PageSettingServiceImpl implements IPageSettingService{

    @Autowired
    private PageSettingMapper pageSettingMapper;

    public PageSetting selectByPrimaryKey(Integer operatorID){

        return pageSettingMapper.selectByPrimaryKey(operatorID);
    }

    @Override
    public Boolean save(PageSetting pageSetting) {
        Integer count=this.pageSettingMapper.insert(pageSetting);
        if(count==1){
           return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean update(PageSetting pageSetting) {
        Integer count=this.pageSettingMapper.updateByPrimaryKey(pageSetting);
        if(count==1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean delete(PageSetting pageSetting) {
        return this.delete(pageSetting.getOperatorID());
    }

    @Override
    public Boolean delete(Integer operatorId) {
        Integer count=this.pageSettingMapper.deleteByPrimaryKey(operatorId);
        if(count==1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public PageSetting findByOperatorId(Integer operatorId) {
        return this.pageSettingMapper.selectByPrimaryKey(operatorId);
    }


}

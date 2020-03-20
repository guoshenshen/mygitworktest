package com.elearning.service.systemManage;


import com.elearning.pojo.systemManage.PageSetting;

public interface IPageSettingService {

    PageSetting selectByPrimaryKey(Integer operatorID);

    public Boolean save(PageSetting pageSetting);

    public Boolean update(PageSetting pageSetting);

    public Boolean delete(PageSetting pageSetting);

    public Boolean delete(Integer operatorId);

    public PageSetting findByOperatorId(Integer operatorId);

}

package com.elearning.service.systemManage;


import com.elearning.pojo.systemManage.TimeSetting;

public interface ITimeSettingService {

    TimeSetting selectByPrimaryKey(Integer ID);

    TimeSetting getCurrenTimeSetting();









}

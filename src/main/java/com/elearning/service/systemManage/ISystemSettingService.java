package com.elearning.service.systemManage;

import com.elearning.pojo.systemManage.TimeSetting;

public interface ISystemSettingService {


    TimeSetting getCurrenTimeSetting();

    void setCurrentSystemTime(String year);

}

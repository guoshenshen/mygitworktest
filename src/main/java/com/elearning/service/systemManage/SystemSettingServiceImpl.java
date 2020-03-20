package com.elearning.service.systemManage;

import com.elearning.pojo.systemManage.TimeSetting;
import org.springframework.stereotype.Service;

@Service("systemSettingService")
public class SystemSettingServiceImpl implements ISystemSettingService{
    @Override
    public TimeSetting getCurrenTimeSetting() {
        return null;
    }

    @Override
    public void setCurrentSystemTime(String year) {

    }
}

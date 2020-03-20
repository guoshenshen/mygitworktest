package com.elearning.service.systemManage;

import com.elearning.dao.systemManage.TimeSettingMapper;
import com.elearning.pojo.systemManage.TimeSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("timeSettingService")
public class TimeSettingServiceImpl implements ITimeSettingService{

    @Autowired
    private TimeSettingMapper timeSettingMapper;



    public TimeSetting selectByPrimaryKey(Integer ID){

        return timeSettingMapper.selectByPrimaryKey(ID);
    }

    public TimeSetting getCurrenTimeSetting(){

        TimeSetting timeSetting = new TimeSetting();
        try{
            timeSetting = this.timeSettingMapper.getCurrenTimeSetting();
        }catch (Exception e) {
            System.out.println("获得系统当前设置出错，请查看数据库中的Status标志位数据是否重复。");
            e.printStackTrace();
        }
        return timeSetting;
    }




}

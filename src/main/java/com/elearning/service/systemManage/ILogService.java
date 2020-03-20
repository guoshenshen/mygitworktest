package com.elearning.service.systemManage;

import com.elearning.pojo.systemManage.Log;

public interface ILogService {

    Log selectByPrimaryKey(Integer logid);


    void save(Log logonLog);
}

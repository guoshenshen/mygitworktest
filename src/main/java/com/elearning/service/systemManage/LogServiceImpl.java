package com.elearning.service.systemManage;

import com.elearning.dao.systemManage.LogMapper;
import com.elearning.pojo.systemManage.Log;
import com.elearning.service.systemManage.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logService")
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;

    public Log selectByPrimaryKey(Integer logid){

        return logMapper.selectByPrimaryKey(logid);
    }

    @Override
    public void save(Log logonLog) {
        this.logMapper.insert(logonLog);
    }


}

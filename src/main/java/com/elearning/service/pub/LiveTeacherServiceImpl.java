package com.elearning.service.pub;

import com.elearning.dao.pub.LiveTeacherMapper;
import com.elearning.pojo.pub.LiveTeacher;
import com.elearning.service.pub.ILiveTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("liveTeacherService")
public class LiveTeacherServiceImpl implements ILiveTeacherService {

    @Autowired
    private LiveTeacherMapper liveTeacherMapper;

    public LiveTeacher selectByPrimaryKey(Integer id){

        return liveTeacherMapper.selectByPrimaryKey(id);
    }


}

package com.elearning.service.pub;

import com.elearning.dao.pub.ClassLiveMapper;
import com.elearning.pojo.pub.ClassLive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("classLiveService")
public class ClassLiveServiceImpl implements IClassLiveService {

    @Autowired
    private ClassLiveMapper classLiveMapper;

    public ClassLive selectByPrimaryKey(Long id){

        return classLiveMapper.selectByPrimaryKey(id);
    }


}

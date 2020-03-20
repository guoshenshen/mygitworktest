package com.elearning.service.pub;

import com.elearning.dao.pub.DynamicLoginMapper;
import com.elearning.pojo.pub.DynamicLogin;
import com.elearning.service.pub.IDynamicLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("dynamicLoginService")
public class DynamicLoginServiceImpl implements IDynamicLoginService {



    @Autowired
    private DynamicLoginMapper dynamicLoginMapper;

    public DynamicLogin selectByPrimaryKey(Integer id){

        return dynamicLoginMapper.selectByPrimaryKey(id);
    }

    @Override
    public DynamicLogin findByDynamicKey(Long dynamicKey) {
        return dynamicLoginMapper.findByDynamicKey(dynamicKey);
    }

    @Override
    public Integer save(DynamicLogin dynamicLogin) {
        Long dynamicKey=new Long(new Date().getTime());
        Integer id=Math.abs(dynamicKey.intValue());
        dynamicLogin.setId(id);
        dynamicLogin.setDynamicKey(dynamicKey);
        return dynamicLoginMapper.insert(dynamicLogin);
    }


}

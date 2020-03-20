package com.elearning.service.pub;

import com.elearning.dao.pub.LoginAuthMapper;
import com.elearning.pojo.pub.LoginAuth;
import com.elearning.service.pub.ILoginAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginAuthService")
public class LoginAuthServiceImpl implements ILoginAuthService {

    @Autowired
    private LoginAuthMapper loginAuthMapper;

    @Override
    public LoginAuth selectByPrimaryKey(Integer id) {

        return loginAuthMapper.selectByPrimaryKey(id);
    }
}

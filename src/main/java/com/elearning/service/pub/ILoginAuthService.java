package com.elearning.service.pub;


import com.elearning.pojo.pub.LoginAuth;

public interface ILoginAuthService {
    LoginAuth selectByPrimaryKey(Integer id);
}

package com.elearning.service.pub;


import com.elearning.pojo.pub.DynamicLogin;

public interface IDynamicLoginService {

    public DynamicLogin selectByPrimaryKey(Integer id);

    public DynamicLogin findByDynamicKey(Long dynamicKey);

    public Integer save(DynamicLogin dynamicLogin);

    public static final Short AUTH_TYPE=0;

    public static final Short PROMPT_LINK_TYPE=1;

    public static final Short PERIOD_LINK_TYPE=2;

}

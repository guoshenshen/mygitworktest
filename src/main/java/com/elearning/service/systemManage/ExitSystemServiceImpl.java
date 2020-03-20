package com.elearning.service.systemManage;

import com.elearning.common.ServiceResponse;
import org.springframework.stereotype.Service;

@Service("exitSystemService")
public class ExitSystemServiceImpl implements IExitSystemService{

    //@Autowired
    //private UserRoleMapper userRoleMapper;

    public ServiceResponse selectByPrimaryKey(Integer ID){

        return ServiceResponse.createBySuccessMessage("成功退出！");
    }


}

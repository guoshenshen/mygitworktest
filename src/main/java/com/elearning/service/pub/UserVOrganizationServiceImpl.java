package com.elearning.service.pub;

import com.elearning.dao.pub.UserVOrganizationMapper;
import com.elearning.pojo.pub.UserVOrganization;
import com.elearning.pojo.pub.UserVOrganizationKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userVOrganizationService")
public class UserVOrganizationServiceImpl implements IUserVOrganizationService {

    @Autowired
    private UserVOrganizationMapper userVOrganizationMapper;

    @Override
    public UserVOrganization selectByPrimaryKey(UserVOrganizationKey key){

        return userVOrganizationMapper.selectByPrimaryKey(key);
    }

    @Override
    public List<UserVOrganization> getUserVorganizationListByOperatorId(Integer operatorId){

        return userVOrganizationMapper.getUserVorganizationListByOperatorId(operatorId);
    }



}

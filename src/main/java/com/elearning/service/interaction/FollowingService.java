package com.elearning.service.interaction;

import com.elearning.dao.interaction.FollowingMapper;
import com.elearning.pojo.interaction.Following;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("followingService")
public class FollowingService implements IFollowingService{

    @Autowired
    private FollowingMapper followingMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public Following selectByPrimaryKey(Integer id){

        return this.followingMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public Integer findFollowsCount(Integer isFollowedBy){

        return this.followingMapper.findFollowsCount(isFollowedBy);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public Integer findFansCount(Integer follows){

        return this.followingMapper.findFansCount(follows);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public Integer findFollowOrgsCount(Integer isFollowedBy){

        return this.followingMapper.findFollowOrgsCount(isFollowedBy);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public Integer getFollowingType(Integer followingId){

        return this.followingMapper.getFollowingType(followingId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<Following> getListByFollowsAndIsFolloweBy(Map<String,Object> parmMap){

        return this.followingMapper.getListByFollowsAndIsFolloweBy(parmMap);
    }

}

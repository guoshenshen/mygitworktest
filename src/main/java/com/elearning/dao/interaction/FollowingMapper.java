package com.elearning.dao.interaction;

import com.elearning.pojo.interaction.Following;

import java.util.List;
import java.util.Map;

public interface FollowingMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Following record);

    int insertSelective(Following record);

    Following selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Following record);

    int updateByPrimaryKey(Following record);

    Integer findFollowsCount(Integer isFollowedBy);

    Integer findFansCount(Integer follows);

    Integer findFollowOrgsCount(Integer isFollowedBy);

    Integer getFollowingType(Integer followingId);

    List<Following> getListByFollowsAndIsFolloweBy(Map<String,Object> parmMap);


}
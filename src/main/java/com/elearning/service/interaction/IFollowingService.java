package com.elearning.service.interaction;

import com.elearning.pojo.interaction.Following;

import java.util.List;
import java.util.Map;

public interface IFollowingService {

    Following selectByPrimaryKey(Integer id);

    List<Following> getListByFollowsAndIsFolloweBy(Map<String,Object> parmMap);

    /**
     * 获取关注人的数目
     * @param isFollowedBy
     * @return
     */
    Integer findFollowsCount(Integer isFollowedBy);

    /**
     * 获取粉丝的数目
     * @param follows
     * @return
     */
    Integer findFansCount(Integer follows);

    /**
     * 获取关注机构的数目
     * @param isFollowedBy
     * @return
     */
    Integer findFollowOrgsCount(Integer isFollowedBy);

    /**
     * 获取关对象的类型
     * @param followingId
     * @return
     */
    Integer getFollowingType(Integer followingId);

}

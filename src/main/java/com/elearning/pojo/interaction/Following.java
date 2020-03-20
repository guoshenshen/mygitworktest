package com.elearning.pojo.interaction;

public class Following {
    private Integer id;

    private Integer follows;

    private Integer isFollowedBy;

    private Integer followsType;

    public Following(Integer id, Integer follows, Integer isFollowedBy, Integer followsType) {
        this.id = id;
        this.follows = follows;
        this.isFollowedBy = isFollowedBy;
        this.followsType = followsType;
    }

    public Following() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFollows() {
        return follows;
    }

    public void setFollows(Integer follows) {
        this.follows = follows;
    }

    public Integer getIsFollowedBy() {
        return isFollowedBy;
    }

    public void setIsFollowedBy(Integer isFollowedBy) {
        this.isFollowedBy = isFollowedBy;
    }

    public Integer getFollowsType() {
        return followsType;
    }

    public void setFollowsType(Integer followsType) {
        this.followsType = followsType;
    }
}
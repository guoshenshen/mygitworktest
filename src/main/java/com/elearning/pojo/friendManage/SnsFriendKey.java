package com.elearning.pojo.friendManage;

public class SnsFriendKey {
    private Integer operatorId;

    private Integer friendId;

    public SnsFriendKey(Integer operatorId, Integer friendId) {
        this.operatorId = operatorId;
        this.friendId = friendId;
    }

    public SnsFriendKey() {
        super();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }
}
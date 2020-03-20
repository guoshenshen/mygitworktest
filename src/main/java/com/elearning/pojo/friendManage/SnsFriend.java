package com.elearning.pojo.friendManage;

public class SnsFriend extends SnsFriendKey {
    private String friendName;

    private Integer groupId;

    private Integer status;

    private String message;

    private String operatorName;

    public SnsFriend(Integer operatorId, Integer friendId, String friendName, Integer groupId, Integer status, String message, String operatorName) {
        super(operatorId, friendId);
        this.friendName = friendName;
        this.groupId = groupId;
        this.status = status;
        this.message = message;
        this.operatorName = operatorName;
    }

    public SnsFriend() {
        super();
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName == null ? null : friendName.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }
}
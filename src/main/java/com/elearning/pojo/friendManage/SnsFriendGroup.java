package com.elearning.pojo.friendManage;

public class SnsFriendGroup {
    private Integer groupId;

    private String groupName;

    private Integer operatorId;

    public SnsFriendGroup(Integer groupId, String groupName, Integer operatorId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.operatorId = operatorId;
    }

    public SnsFriendGroup() {
        super();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}
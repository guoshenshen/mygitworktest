package com.elearning.pojo.systemManage;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Role {
    private Integer ID;

    private String roleName;

    private Integer isVirRole;

    private Integer orgId;

    private Integer creatorId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    private String comment;

    private String roleCode;

    private String roleUrl;

    private String orgsname;

    private Integer parentOrgId;

    private Integer defaultResourceId;

    public Role(Integer ID, String roleName, Integer isVirRole, Integer orgId, Integer creatorId, Date createTime, String comment, String roleCode, String roleUrl, String orgsname, Integer parentOrgId, Integer defaultResourceId) {
        this.ID = ID;
        this.roleName = roleName;
        this.isVirRole = isVirRole;
        this.orgId = orgId;
        this.creatorId = creatorId;
        this.createTime = createTime;
        this.comment = comment;
        this.roleCode = roleCode;
        this.roleUrl = roleUrl;
        this.orgsname = orgsname;
        this.parentOrgId = parentOrgId;
        this.defaultResourceId = defaultResourceId;
    }

    public Role() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getIsVirRole() {
        return isVirRole;
    }

    public void setIsVirRole(Integer isVirRole) {
        this.isVirRole = isVirRole;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleUrl() {
        return roleUrl;
    }

    public void setRoleUrl(String roleUrl) {
        this.roleUrl = roleUrl == null ? null : roleUrl.trim();
    }

    public String getOrgsname() {
        return orgsname;
    }

    public void setOrgsname(String orgsname) {
        this.orgsname = orgsname == null ? null : orgsname.trim();
    }

    public Integer getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(Integer parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public Integer getDefaultResourceId() {
        return defaultResourceId;
    }

    public void setDefaultResourceId(Integer defaultResourceId) {
        this.defaultResourceId = defaultResourceId;
    }
}
package com.elearning.vo.pub;

import com.elearning.vo.pub.OrgseqGetter;

import java.io.Serializable;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/30 9:01
 */
public class BasicOrgForm implements Serializable,OrgseqGetter {

    private Integer orgId;

    private String orgName;

    private String orgCode;

    private String orgSeq;

    private String logo;

    private Integer status;

    private Integer isOrg;

    private Integer isVirOrg;

    private Integer tenantId;

    private String tenantName;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgSeq() {
        return orgSeq;
    }

    public void setOrgSeq(String orgSeq) {
        this.orgSeq = orgSeq;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsOrg() {
        return isOrg;
    }

    public void setIsOrg(Integer isOrg) {
        this.isOrg = isOrg;
    }

    public Integer getIsVirOrg() {
        return isVirOrg;
    }

    public void setIsVirOrg(Integer isVirOrg) {
        this.isVirOrg = isVirOrg;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
}

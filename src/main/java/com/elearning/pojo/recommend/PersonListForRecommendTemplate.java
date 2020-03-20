package com.elearning.pojo.recommend;

public class PersonListForRecommendTemplate {
    private Integer id;

    private Integer adminOperatorId;

    private Integer adminOrgId;

    private Integer memberId;

    private Integer memberType;

    private Long recommendMailId;

    public PersonListForRecommendTemplate(Integer id, Integer adminOperatorId, Integer adminOrgId, Integer memberId, Integer memberType, Long recommendMailId) {
        this.id = id;
        this.adminOperatorId = adminOperatorId;
        this.adminOrgId = adminOrgId;
        this.memberId = memberId;
        this.memberType = memberType;
        this.recommendMailId = recommendMailId;
    }

    public PersonListForRecommendTemplate() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminOperatorId() {
        return adminOperatorId;
    }

    public void setAdminOperatorId(Integer adminOperatorId) {
        this.adminOperatorId = adminOperatorId;
    }

    public Integer getAdminOrgId() {
        return adminOrgId;
    }

    public void setAdminOrgId(Integer adminOrgId) {
        this.adminOrgId = adminOrgId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public Long getRecommendMailId() {
        return recommendMailId;
    }

    public void setRecommendMailId(Long recommendMailId) {
        this.recommendMailId = recommendMailId;
    }
}
package com.elearning.pojo.pub;

public class BasicResearch {
    private Integer id;

    private String orgcode;

    private String orgname;

    private Integer tenantId;

    private Integer orgID;

    private Integer trainNum;

    private Integer implementedTrainNum;

    private Integer trainPeopleCount;

    private Float implementRate;

    private Integer year;

    private Integer order;

    private String orgcode_e;

    private Integer isTakeAssessment;

    public BasicResearch(Integer id, String orgcode, String orgname, Integer tenantId, Integer orgID, Integer trainNum, Integer implementedTrainNum, Integer trainPeopleCount, Float implementRate, Integer year, Integer order, String orgcode_e, Integer isTakeAssessment) {
        this.id = id;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.tenantId = tenantId;
        this.orgID = orgID;
        this.trainNum = trainNum;
        this.implementedTrainNum = implementedTrainNum;
        this.trainPeopleCount = trainPeopleCount;
        this.implementRate = implementRate;
        this.year = year;
        this.order = order;
        this.orgcode_e = orgcode_e;
        this.isTakeAssessment = isTakeAssessment;
    }

    public BasicResearch() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode == null ? null : orgcode.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public Integer getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(Integer trainNum) {
        this.trainNum = trainNum;
    }

    public Integer getImplementedTrainNum() {
        return implementedTrainNum;
    }

    public void setImplementedTrainNum(Integer implementedTrainNum) {
        this.implementedTrainNum = implementedTrainNum;
    }

    public Integer getTrainPeopleCount() {
        return trainPeopleCount;
    }

    public void setTrainPeopleCount(Integer trainPeopleCount) {
        this.trainPeopleCount = trainPeopleCount;
    }

    public Float getImplementRate() {
        return implementRate;
    }

    public void setImplementRate(Float implementRate) {
        this.implementRate = implementRate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getOrgcode_e() {
        return orgcode_e;
    }

    public void setOrgcode_e(String orgcode_e) {
        this.orgcode_e = orgcode_e == null ? null : orgcode_e.trim();
    }

    public Integer getIsTakeAssessment() {
        return isTakeAssessment;
    }

    public void setIsTakeAssessment(Integer isTakeAssessment) {
        this.isTakeAssessment = isTakeAssessment;
    }
}
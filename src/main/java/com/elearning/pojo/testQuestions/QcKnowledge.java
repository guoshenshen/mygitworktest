package com.elearning.pojo.testQuestions;

public class QcKnowledge {
    private Integer ID;

    private String knowPoint;

    private String knowDescription;

    private Integer parentId;

    private String fullPath;

    private Integer kindex;

    private Integer tenantId;

    public QcKnowledge(Integer ID, String knowPoint, String knowDescription, Integer parentId, String fullPath, Integer kindex, Integer tenantId) {
        this.ID = ID;
        this.knowPoint = knowPoint;
        this.knowDescription = knowDescription;
        this.parentId = parentId;
        this.fullPath = fullPath;
        this.kindex = kindex;
        this.tenantId = tenantId;
    }

    public QcKnowledge() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getKnowPoint() {
        return knowPoint;
    }

    public void setKnowPoint(String knowPoint) {
        this.knowPoint = knowPoint == null ? null : knowPoint.trim();
    }

    public String getKnowDescription() {
        return knowDescription;
    }

    public void setKnowDescription(String knowDescription) {
        this.knowDescription = knowDescription == null ? null : knowDescription.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath == null ? null : fullPath.trim();
    }

    public Integer getKindex() {
        return kindex;
    }

    public void setKindex(Integer kindex) {
        this.kindex = kindex;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}
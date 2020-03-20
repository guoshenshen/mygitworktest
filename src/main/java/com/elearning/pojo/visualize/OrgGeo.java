package com.elearning.pojo.visualize;

public class OrgGeo {
    private Integer id;

    private Integer orgId;

    private Integer geoId;

    public OrgGeo(Integer id, Integer orgId, Integer geoId) {
        this.id = id;
        this.orgId = orgId;
        this.geoId = geoId;
    }

    public OrgGeo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getGeoId() {
        return geoId;
    }

    public void setGeoId(Integer geoId) {
        this.geoId = geoId;
    }
}
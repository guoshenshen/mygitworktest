package com.elearning.pojo.pub;

public class DDictionary {
    private Integer ID;

    private String code;

    private String parentCode;

    private String name;

    private Integer visible;

    private Integer addItemAble;

    private Integer seq;

    private String pictureURL;

    private String remark;

    private Integer mngStatus;

    private Integer tenantId;

    private Integer isClassCorner;

    public DDictionary(Integer ID, String code, String parentCode, String name, Integer visible, Integer addItemAble, Integer seq, String pictureURL, String remark, Integer mngStatus, Integer tenantId, Integer isClassCorner) {
        this.ID = ID;
        this.code = code;
        this.parentCode = parentCode;
        this.name = name;
        this.visible = visible;
        this.addItemAble = addItemAble;
        this.seq = seq;
        this.pictureURL = pictureURL;
        this.remark = remark;
        this.mngStatus = mngStatus;
        this.tenantId = tenantId;
        this.isClassCorner = isClassCorner;
    }

    public DDictionary() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getAddItemAble() {
        return addItemAble;
    }

    public void setAddItemAble(Integer addItemAble) {
        this.addItemAble = addItemAble;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL == null ? null : pictureURL.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getMngStatus() {
        return mngStatus;
    }

    public void setMngStatus(Integer mngStatus) {
        this.mngStatus = mngStatus;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getIsClassCorner() {
        return isClassCorner;
    }

    public void setIsClassCorner(Integer isClassCorner) {
        this.isClassCorner = isClassCorner;
    }
}
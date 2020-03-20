package com.elearning.pojo.message;

import java.util.Date;

public class MsgMessageInfo {
    private Integer ID;

    private String title;

    private Integer status;

    private Date validDate;

    private Integer inSystem;

    private Integer isEmail;

    private Integer editorID;

    private Integer isPublic;

    private Date invalidDate;

    private Integer hasSend;

    private String attachmentPath;

    private Integer backflag;

    private Integer trainID;

    private Integer isSMS;

    private Integer tenantId;

    private String smsContent;

    private Integer recommendTag;

    private Integer confirmType;

    private String ewm;

    private Integer ewmType;

    private String code;

    public MsgMessageInfo(Integer ID, String title, Integer status, Date validDate, Integer inSystem, Integer isEmail, Integer editorID, Integer isPublic, Date invalidDate, Integer hasSend, String attachmentPath, Integer backflag, Integer trainID, Integer isSMS, Integer tenantId, String smsContent, Integer recommendTag, Integer confirmType, String ewm, Integer ewmType, String code) {
        this.ID = ID;
        this.title = title;
        this.status = status;
        this.validDate = validDate;
        this.inSystem = inSystem;
        this.isEmail = isEmail;
        this.editorID = editorID;
        this.isPublic = isPublic;
        this.invalidDate = invalidDate;
        this.hasSend = hasSend;
        this.attachmentPath = attachmentPath;
        this.backflag = backflag;
        this.trainID = trainID;
        this.isSMS = isSMS;
        this.tenantId = tenantId;
        this.smsContent = smsContent;
        this.recommendTag = recommendTag;
        this.confirmType = confirmType;
        this.ewm = ewm;
        this.ewmType = ewmType;
        this.code = code;
    }

    public MsgMessageInfo() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Integer getInSystem() {
        return inSystem;
    }

    public void setInSystem(Integer inSystem) {
        this.inSystem = inSystem;
    }

    public Integer getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(Integer isEmail) {
        this.isEmail = isEmail;
    }

    public Integer getEditorID() {
        return editorID;
    }

    public void setEditorID(Integer editorID) {
        this.editorID = editorID;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public Integer getHasSend() {
        return hasSend;
    }

    public void setHasSend(Integer hasSend) {
        this.hasSend = hasSend;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath == null ? null : attachmentPath.trim();
    }

    public Integer getBackflag() {
        return backflag;
    }

    public void setBackflag(Integer backflag) {
        this.backflag = backflag;
    }

    public Integer getTrainID() {
        return trainID;
    }

    public void setTrainID(Integer trainID) {
        this.trainID = trainID;
    }

    public Integer getIsSMS() {
        return isSMS;
    }

    public void setIsSMS(Integer isSMS) {
        this.isSMS = isSMS;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent == null ? null : smsContent.trim();
    }

    public Integer getRecommendTag() {
        return recommendTag;
    }

    public void setRecommendTag(Integer recommendTag) {
        this.recommendTag = recommendTag;
    }

    public Integer getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(Integer confirmType) {
        this.confirmType = confirmType;
    }

    public String getEwm() {
        return ewm;
    }

    public void setEwm(String ewm) {
        this.ewm = ewm == null ? null : ewm.trim();
    }

    public Integer getEwmType() {
        return ewmType;
    }

    public void setEwmType(Integer ewmType) {
        this.ewmType = ewmType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}
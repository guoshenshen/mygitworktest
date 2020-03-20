package com.elearning.vo.message;

import java.io.File;
import java.io.Serializable;
import java.util.List;


public class MsgMessageInfoForm implements Serializable{

    private Integer id;

    private Integer editorId;

    private String title;

    private String content;

    private String smscontent;

    private Integer status;

    private String invalidDate;

    private String validDate;

    private Integer inSystem;

    private Integer isEmail;

    private Integer isPublic;

    private Integer isSMS;

    private Integer hasSend;

    private String statusName;

    private String messageType;

    private String tipType;

    private File attachmentFile;

    private String attachmentPath;

    private String publicer;//发布人

    private List<Integer> studentIdList;

    private List<Integer> secretaryIdList;

    private List<Integer> directorIdList;

    private Integer backflag;

    private Integer trainId;

    private String trainName;

    private String publicName;

    private String messageCategory;

    private Integer tenantId;

    private Integer recommendTag;

    private String orgName;

    private Integer confirmType;
    // Constructors

    private List<Integer> deptList;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSmscontent() {
        return smscontent;
    }

    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
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

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getIsSMS() {
        return isSMS;
    }

    public void setIsSMS(Integer isSMS) {
        this.isSMS = isSMS;
    }

    public Integer getHasSend() {
        return hasSend;
    }

    public void setHasSend(Integer hasSend) {
        this.hasSend = hasSend;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getTipType() {
        return tipType;
    }

    public void setTipType(String tipType) {
        this.tipType = tipType;
    }

    public File getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(File attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getPublicer() {
        return publicer;
    }

    public void setPublicer(String publicer) {
        this.publicer = publicer;
    }

    public List<Integer> getStudentIdList() {
        return studentIdList;
    }

    public void setStudentIdList(List<Integer> studentIdList) {
        this.studentIdList = studentIdList;
    }

    public List<Integer> getSecretaryIdList() {
        return secretaryIdList;
    }

    public void setSecretaryIdList(List<Integer> secretaryIdList) {
        this.secretaryIdList = secretaryIdList;
    }

    public List<Integer> getDirectorIdList() {
        return directorIdList;
    }

    public void setDirectorIdList(List<Integer> directorIdList) {
        this.directorIdList = directorIdList;
    }

    public Integer getBackflag() {
        return backflag;
    }

    public void setBackflag(Integer backflag) {
        this.backflag = backflag;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getMessageCategory() {
        return messageCategory;
    }

    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getRecommendTag() {
        return recommendTag;
    }

    public void setRecommendTag(Integer recommendTag) {
        this.recommendTag = recommendTag;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(Integer confirmType) {
        this.confirmType = confirmType;
    }

    public List<Integer> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<Integer> deptList) {
        this.deptList = deptList;
    }
}

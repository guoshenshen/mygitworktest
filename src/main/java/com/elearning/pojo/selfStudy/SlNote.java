package com.elearning.pojo.selfStudy;

import java.util.Date;

public class SlNote {
    private Integer slNoteId;

    private String noteTitle;

    private String noteDescription;

    private String noteContent;

    private String attachFileName;

    private String attachFileUrl;

    private String attachFileType;

    private Integer operatorId;

    private Date createDate;

    private String extParam;

    public SlNote(Integer slNoteId, String noteTitle, String noteDescription, String noteContent, String attachFileName, String attachFileUrl, String attachFileType, Integer operatorId, Date createDate, String extParam) {
        this.slNoteId = slNoteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.noteContent = noteContent;
        this.attachFileName = attachFileName;
        this.attachFileUrl = attachFileUrl;
        this.attachFileType = attachFileType;
        this.operatorId = operatorId;
        this.createDate = createDate;
        this.extParam = extParam;
    }

    public SlNote() {
        super();
    }

    public Integer getSlNoteId() {
        return slNoteId;
    }

    public void setSlNoteId(Integer slNoteId) {
        this.slNoteId = slNoteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle == null ? null : noteTitle.trim();
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription == null ? null : noteDescription.trim();
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent == null ? null : noteContent.trim();
    }

    public String getAttachFileName() {
        return attachFileName;
    }

    public void setAttachFileName(String attachFileName) {
        this.attachFileName = attachFileName == null ? null : attachFileName.trim();
    }

    public String getAttachFileUrl() {
        return attachFileUrl;
    }

    public void setAttachFileUrl(String attachFileUrl) {
        this.attachFileUrl = attachFileUrl == null ? null : attachFileUrl.trim();
    }

    public String getAttachFileType() {
        return attachFileType;
    }

    public void setAttachFileType(String attachFileType) {
        this.attachFileType = attachFileType == null ? null : attachFileType.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getExtParam() {
        return extParam;
    }

    public void setExtParam(String extParam) {
        this.extParam = extParam == null ? null : extParam.trim();
    }
}
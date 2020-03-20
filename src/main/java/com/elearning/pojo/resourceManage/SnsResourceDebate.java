package com.elearning.pojo.resourceManage;

public class SnsResourceDebate {
    private Integer debateId;

    private Integer parentDebateId;

    private Long resourceId;

    private String content;

    private Integer score;

    private String fileName;

    private String filePath;

    private String fileType;

    private String fileSize;

    private Integer status;

    private Integer operatorId;

    public SnsResourceDebate(Integer debateId, Integer parentDebateId, Long resourceId, String content, Integer score, String fileName, String filePath, String fileType, String fileSize, Integer status, Integer operatorId) {
        this.debateId = debateId;
        this.parentDebateId = parentDebateId;
        this.resourceId = resourceId;
        this.content = content;
        this.score = score;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.status = status;
        this.operatorId = operatorId;
    }

    public SnsResourceDebate() {
        super();
    }

    public Integer getDebateId() {
        return debateId;
    }

    public void setDebateId(Integer debateId) {
        this.debateId = debateId;
    }

    public Integer getParentDebateId() {
        return parentDebateId;
    }

    public void setParentDebateId(Integer parentDebateId) {
        this.parentDebateId = parentDebateId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}
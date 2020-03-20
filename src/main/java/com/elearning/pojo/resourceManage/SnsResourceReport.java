package com.elearning.pojo.resourceManage;

public class SnsResourceReport {
    private Integer reportId;

    private Long resourceId;

    private String reportContent;

    private Integer reportReasonType;

    private Integer reportorId;

    private String replyContent;

    private Integer status;

    public SnsResourceReport(Integer reportId, Long resourceId, String reportContent, Integer reportReasonType, Integer reportorId, String replyContent, Integer status) {
        this.reportId = reportId;
        this.resourceId = resourceId;
        this.reportContent = reportContent;
        this.reportReasonType = reportReasonType;
        this.reportorId = reportorId;
        this.replyContent = replyContent;
        this.status = status;
    }

    public SnsResourceReport() {
        super();
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent == null ? null : reportContent.trim();
    }

    public Integer getReportReasonType() {
        return reportReasonType;
    }

    public void setReportReasonType(Integer reportReasonType) {
        this.reportReasonType = reportReasonType;
    }

    public Integer getReportorId() {
        return reportorId;
    }

    public void setReportorId(Integer reportorId) {
        this.reportorId = reportorId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
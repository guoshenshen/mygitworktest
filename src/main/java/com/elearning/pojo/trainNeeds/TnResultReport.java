package com.elearning.pojo.trainNeeds;

import java.util.Date;

public class TnResultReport {
    private Integer id;

    private Integer tnId;

    private Date date;

    private Integer operatorId;

    private String content;

    private String url;

    private String reportName;

    public TnResultReport(Integer id, Integer tnId, Date date, Integer operatorId, String content, String url, String reportName) {
        this.id = id;
        this.tnId = tnId;
        this.date = date;
        this.operatorId = operatorId;
        this.content = content;
        this.url = url;
        this.reportName = reportName;
    }

    public TnResultReport() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTnId() {
        return tnId;
    }

    public void setTnId(Integer tnId) {
        this.tnId = tnId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName == null ? null : reportName.trim();
    }
}
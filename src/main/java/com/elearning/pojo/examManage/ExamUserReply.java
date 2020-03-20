package com.elearning.pojo.examManage;

import java.util.Date;

public class ExamUserReply {
    private Integer id;

    private Integer exam_id;

    private Integer operatorId;

    private Date submitDate;

    private Integer paperId;

    public ExamUserReply(Integer id, Integer exam_id, Integer operatorId, Date submitDate, Integer paperId) {
        this.id = id;
        this.exam_id = exam_id;
        this.operatorId = operatorId;
        this.submitDate = submitDate;
        this.paperId = paperId;
    }

    public ExamUserReply() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExam_id() {
        return exam_id;
    }

    public void setExam_id(Integer exam_id) {
        this.exam_id = exam_id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }
}
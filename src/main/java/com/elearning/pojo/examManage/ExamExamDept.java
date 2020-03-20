package com.elearning.pojo.examManage;

public class ExamExamDept {
    private Integer id;

    private Integer exam_id;

    private Integer orgid;

    public ExamExamDept(Integer id, Integer exam_id, Integer orgid) {
        this.id = id;
        this.exam_id = exam_id;
        this.orgid = orgid;
    }

    public ExamExamDept() {
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

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }
}
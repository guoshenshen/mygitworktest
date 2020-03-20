package com.elearning.pojo.examManage;

public class ExamExamUser {
    private Integer id;

    private Integer exam_id;

    private Integer operatorId;

    public ExamExamUser(Integer id, Integer exam_id, Integer operatorId) {
        this.id = id;
        this.exam_id = exam_id;
        this.operatorId = operatorId;
    }

    public ExamExamUser() {
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
}
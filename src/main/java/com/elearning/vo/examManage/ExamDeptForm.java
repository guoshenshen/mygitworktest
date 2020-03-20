package com.elearning.vo.examManage;

public class ExamDeptForm {

    private static final long serialVersionUID = 4830286196580591037L;

    private Integer id;
    private Integer exam_Id;
    private Integer orgId;
    private String orgName;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getExam_Id() {
        return exam_Id;
    }
    public void setExam_Id(Integer exam_Id) {
        this.exam_Id = exam_Id;
    }
    public Integer getOrgId() {
        return orgId;
    }
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

}

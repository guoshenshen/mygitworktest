package com.elearning.vo.material;

import java.util.Date;

public class AssignUserForm {

    private Long assignId;

    private Integer operatorId;

    private String operatorName;

    /**
     *  学员对该作业的处理状态
     */
    private Short status;

    /**
     * 作业创建日期
     */
    private Date createDate;

    /**
     * 用户提交作业时间
     */
    private Date submitTime;

    /**
     * 作业描述
     */
    private String description;

    /**
     * 作业备注
     */
    private String comment;


    /**
     * 作业隶属资源模块类型（培训、课件、专题）
     */
    private Short type;

    /**
     * 隶属资源模块id
     */
    private Long resourceId;


    /**
     * 作业名称
     * */
    private String assignName;

    /**
     * 作业创建人员
     * */
    private String assignCreatorName;

    public Long getAssignId() {
        return assignId;
    }

    public void setAssignId(Long assignId) {
        this.assignId = assignId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public String getAssignCreatorName() {
        return assignCreatorName;
    }

    public void setAssignCreatorName(String assignCreatorName) {
        this.assignCreatorName = assignCreatorName;
    }

    public AssignUserForm(){
    }
}

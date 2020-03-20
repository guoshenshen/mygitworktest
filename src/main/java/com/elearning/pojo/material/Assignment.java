package com.elearning.pojo.material;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Assignment {

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private Long id;

    private String name;

    private Date createDate;

    //gss--add--前端需要
    private String createDateStr;

    private String creatorName;

    private Boolean type;

    private Long resourceId;

    private String description;

    private String comment;

    public Assignment(Long id, String name, Date createDate, String creatorName, Boolean type, Long resourceId, String description, String comment) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.creatorName = creatorName;
        this.type = type;
        this.resourceId = resourceId;
        this.description = description;
        this.comment = comment;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public Assignment() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}
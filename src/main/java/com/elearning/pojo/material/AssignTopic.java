package com.elearning.pojo.material;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AssignTopic {

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private Long id;

    private Long assignId;

    private Integer index1;

    private Boolean required;

    private Boolean form;

    private String content;

    public AssignTopic(Long id, Long assignId, Integer index1, Boolean required, Boolean form, String content) {
        this.id = id;
        this.assignId = assignId;
        this.index1 = index1;
        this.required = required;
        this.form = form;
        this.content = content;
    }

    public AssignTopic() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssignId() {
        return assignId;
    }

    public void setAssignId(Long assignId) {
        this.assignId = assignId;
    }

    public Integer getIndex1() {
        return index1;
    }

    public void setIndex1(Integer index1) {
        this.index1 = index1;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getForm() {
        return form;
    }

    public void setForm(Boolean form) {
        this.form = form;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
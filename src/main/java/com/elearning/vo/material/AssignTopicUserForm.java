package com.elearning.vo.material;

import com.elearning.pojo.material.AssignTopic;

public class AssignTopicUserForm {

    private Long id;
    private Long topicId;
    private String topicContent;
    private Integer index;
    private Boolean required;
    private Boolean form;
    private Integer operatorId;
    private String content;

    public AssignTopicUserForm() {}

    public AssignTopicUserForm(Long id, Long topicId, String topicContent, Integer index, Boolean required, Boolean form, Integer operatorId, String content) {
        this.id = id;
        this.topicId = topicId;
        this.topicContent = topicContent;
        this.index = index;
        this.required = required;
        this.form = form;
        this.operatorId = operatorId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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
        this.content = content;
    }

    public AssignTopicUserForm(AssignTopic assignTopic){
        this.topicId = assignTopic.getId();
        this.topicContent = assignTopic.getContent();
        this.index = assignTopic.getIndex1();
        this.required = assignTopic.getRequired();
        this.form = assignTopic.getForm();
    }

}

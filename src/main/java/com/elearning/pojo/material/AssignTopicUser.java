package com.elearning.pojo.material;

public class AssignTopicUser {
    private Long id;

    private Long topicId;

    private Integer operatorId;

    private String content;

    public AssignTopicUser(Long id, Long topicId, Integer operatorId, String content) {
        this.id = id;
        this.topicId = topicId;
        this.operatorId = operatorId;
        this.content = content;
    }

    public AssignTopicUser() {
        super();
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
}
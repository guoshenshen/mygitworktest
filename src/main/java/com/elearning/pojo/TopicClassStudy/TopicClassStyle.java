package com.elearning.pojo.TopicClassStudy;

import java.util.Date;

public class TopicClassStyle {
    private Integer id;

    private Integer identifyKey;

    private Integer type;

    private String pageTemplate;

    private String headPictureUrl;

    private String bodyBackgroundcolor;

    private String titlecolor;

    private String bannerleftcolor;

    private String bannerrightcolor;

    private Date createTime;

    private String targetUrl;

    private String topicClassName;

    public TopicClassStyle(Integer id, Integer identifyKey, Integer type, String pageTemplate, String headPictureUrl, String bodyBackgroundcolor, String titlecolor, String bannerleftcolor, String bannerrightcolor, Date createTime, String targetUrl, String topicClassName) {
        this.id = id;
        this.identifyKey = identifyKey;
        this.type = type;
        this.pageTemplate = pageTemplate;
        this.headPictureUrl = headPictureUrl;
        this.bodyBackgroundcolor = bodyBackgroundcolor;
        this.titlecolor = titlecolor;
        this.bannerleftcolor = bannerleftcolor;
        this.bannerrightcolor = bannerrightcolor;
        this.createTime = createTime;
        this.targetUrl = targetUrl;
        this.topicClassName = topicClassName;
    }

    public TopicClassStyle() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdentifyKey() {
        return identifyKey;
    }

    public void setIdentifyKey(Integer identifyKey) {
        this.identifyKey = identifyKey;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPageTemplate() {
        return pageTemplate;
    }

    public void setPageTemplate(String pageTemplate) {
        this.pageTemplate = pageTemplate == null ? null : pageTemplate.trim();
    }

    public String getHeadPictureUrl() {
        return headPictureUrl;
    }

    public void setHeadPictureUrl(String headPictureUrl) {
        this.headPictureUrl = headPictureUrl == null ? null : headPictureUrl.trim();
    }

    public String getBodyBackgroundcolor() {
        return bodyBackgroundcolor;
    }

    public void setBodyBackgroundcolor(String bodyBackgroundcolor) {
        this.bodyBackgroundcolor = bodyBackgroundcolor == null ? null : bodyBackgroundcolor.trim();
    }

    public String getTitlecolor() {
        return titlecolor;
    }

    public void setTitlecolor(String titlecolor) {
        this.titlecolor = titlecolor == null ? null : titlecolor.trim();
    }

    public String getBannerleftcolor() {
        return bannerleftcolor;
    }

    public void setBannerleftcolor(String bannerleftcolor) {
        this.bannerleftcolor = bannerleftcolor == null ? null : bannerleftcolor.trim();
    }

    public String getBannerrightcolor() {
        return bannerrightcolor;
    }

    public void setBannerrightcolor(String bannerrightcolor) {
        this.bannerrightcolor = bannerrightcolor == null ? null : bannerrightcolor.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl == null ? null : targetUrl.trim();
    }

    public String getTopicClassName() {
        return topicClassName;
    }

    public void setTopicClassName(String topicClassName) {
        this.topicClassName = topicClassName == null ? null : topicClassName.trim();
    }
}
package com.elearning.pojo.coursemanage;

public class ItemInfo {
    private Long itemId;

    private String identifier;

    private String type;

    private String title;

    private String launch;

    private String parameterString;

    private String dataFromLMS;

    private String prerequisites;

    private String masteryScore;

    private String maxTimeAllowed;

    private String timeLimitAction;

    private Integer sequence;

    private Integer theLevel;

    private Long courseId;

    public ItemInfo(Long itemId, String identifier, String type, String title, String launch, String parameterString, String dataFromLMS, String prerequisites, String masteryScore, String maxTimeAllowed, String timeLimitAction, Integer sequence, Integer theLevel, Long courseId) {
        this.itemId = itemId;
        this.identifier = identifier;
        this.type = type;
        this.title = title;
        this.launch = launch;
        this.parameterString = parameterString;
        this.dataFromLMS = dataFromLMS;
        this.prerequisites = prerequisites;
        this.masteryScore = masteryScore;
        this.maxTimeAllowed = maxTimeAllowed;
        this.timeLimitAction = timeLimitAction;
        this.sequence = sequence;
        this.theLevel = theLevel;
        this.courseId = courseId;
    }

    public ItemInfo() {
        super();
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier == null ? null : identifier.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getLaunch() {
        return launch;
    }

    public void setLaunch(String launch) {
        this.launch = launch == null ? null : launch.trim();
    }

    public String getParameterString() {
        return parameterString;
    }

    public void setParameterString(String parameterString) {
        this.parameterString = parameterString == null ? null : parameterString.trim();
    }

    public String getDataFromLMS() {
        return dataFromLMS;
    }

    public void setDataFromLMS(String dataFromLMS) {
        this.dataFromLMS = dataFromLMS == null ? null : dataFromLMS.trim();
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites == null ? null : prerequisites.trim();
    }

    public String getMasteryScore() {
        return masteryScore;
    }

    public void setMasteryScore(String masteryScore) {
        this.masteryScore = masteryScore == null ? null : masteryScore.trim();
    }

    public String getMaxTimeAllowed() {
        return maxTimeAllowed;
    }

    public void setMaxTimeAllowed(String maxTimeAllowed) {
        this.maxTimeAllowed = maxTimeAllowed == null ? null : maxTimeAllowed.trim();
    }

    public String getTimeLimitAction() {
        return timeLimitAction;
    }

    public void setTimeLimitAction(String timeLimitAction) {
        this.timeLimitAction = timeLimitAction == null ? null : timeLimitAction.trim();
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getTheLevel() {
        return theLevel;
    }

    public void setTheLevel(Integer theLevel) {
        this.theLevel = theLevel;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
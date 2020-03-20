package com.elearning.pojo.coursemanage;

public class CourseVideoConvert {
    private Integer id;

    private Long courseId;

    private Integer taskId;

    private String convertDescription;

    public CourseVideoConvert(Integer id, Long courseId, Integer taskId, String convertDescription) {
        this.id = id;
        this.courseId = courseId;
        this.taskId = taskId;
        this.convertDescription = convertDescription;
    }

    public CourseVideoConvert() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getConvertDescription() {
        return convertDescription;
    }

    public void setConvertDescription(String convertDescription) {
        this.convertDescription = convertDescription == null ? null : convertDescription.trim();
    }
}
package com.elearning.pojo.coursemanage;

public class CourseKeytagKey {
    private Long courseId;

    private String keyWords;

    public CourseKeytagKey(Long courseId, String keyWords) {
        this.courseId = courseId;
        this.keyWords = keyWords;
    }

    public CourseKeytagKey() {
        super();
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords == null ? null : keyWords.trim();
    }
}
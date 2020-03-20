package com.elearning.pojo.coursemanage;

public class CourseRelevantcourse {
    private Integer id;

    private Long course_id;

    private Long original_course_id;

    private String courseName;

    public CourseRelevantcourse(Integer id, Long course_id, Long original_course_id) {
        this.id = id;
        this.course_id = course_id;
        this.original_course_id = original_course_id;
    }

    public CourseRelevantcourse() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Long getOriginal_course_id() {
        return original_course_id;
    }

    public void setOriginal_course_id(Long original_course_id) {
        this.original_course_id = original_course_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
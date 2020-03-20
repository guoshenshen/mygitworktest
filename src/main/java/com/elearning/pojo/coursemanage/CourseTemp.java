package com.elearning.pojo.coursemanage;

public class CourseTemp {
    private Integer id;

    private Long course_id;

    private Long category;

    public CourseTemp(Integer id, Long course_id, Long category) {
        this.id = id;
        this.course_id = course_id;
        this.category = category;
    }

    public CourseTemp() {
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

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }
}
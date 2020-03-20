package com.elearning.pojo.coursemanage;

public class CourseCoursetype {
    private Integer ID;

    private Long course_id;

    private Integer courseType_ID;

    public CourseCoursetype(Integer ID, Long course_id, Integer courseType_ID) {
        this.ID = ID;
        this.course_id = course_id;
        this.courseType_ID = courseType_ID;
    }

    public CourseCoursetype() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Integer getCourseType_ID() {
        return courseType_ID;
    }

    public void setCourseType_ID(Integer courseType_ID) {
        this.courseType_ID = courseType_ID;
    }
}
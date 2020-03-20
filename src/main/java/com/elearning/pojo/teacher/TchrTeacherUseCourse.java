package com.elearning.pojo.teacher;

public class TchrTeacherUseCourse {
    private Integer id;

    private Integer teacherID;

    private Long courseID;

    private Integer isCourse;

    private String courseName;

    private String materialPath;

    public TchrTeacherUseCourse(Integer id, Integer teacherID, Long courseID, Integer isCourse, String courseName, String materialPath) {
        this.id = id;
        this.teacherID = teacherID;
        this.courseID = courseID;
        this.isCourse = isCourse;
        this.courseName = courseName;
        this.materialPath = materialPath;
    }

    public TchrTeacherUseCourse() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public Integer getIsCourse() {
        return isCourse;
    }

    public void setIsCourse(Integer isCourse) {
        this.isCourse = isCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getMaterialPath() {
        return materialPath;
    }

    public void setMaterialPath(String materialPath) {
        this.materialPath = materialPath == null ? null : materialPath.trim();
    }
}
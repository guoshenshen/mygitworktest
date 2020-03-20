package com.elearning.pojo.courseStudy;

public class CourseNotebook {
    private Integer ID;

    private Integer opeateorID;

    private Long courseId;

    private String noteContent;

    private String SCOID;

    public CourseNotebook(Integer ID, Integer opeateorID, Long courseId, String noteContent, String SCOID) {
        this.ID = ID;
        this.opeateorID = opeateorID;
        this.courseId = courseId;
        this.noteContent = noteContent;
        this.SCOID = SCOID;
    }

    public CourseNotebook() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getOpeateorID() {
        return opeateorID;
    }

    public void setOpeateorID(Integer opeateorID) {
        this.opeateorID = opeateorID;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent == null ? null : noteContent.trim();
    }

    public String getSCOID() {
        return SCOID;
    }

    public void setSCOID(String SCOID) {
        this.SCOID = SCOID == null ? null : SCOID.trim();
    }
}
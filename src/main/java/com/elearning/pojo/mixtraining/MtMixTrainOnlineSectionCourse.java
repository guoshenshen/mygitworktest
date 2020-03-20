package com.elearning.pojo.mixtraining;

public class MtMixTrainOnlineSectionCourse {
    private Integer ID;

    private Integer section_id;

    private Long courseID;

    public MtMixTrainOnlineSectionCourse(Integer ID, Integer section_id, Long courseID) {
        this.ID = ID;
        this.section_id = section_id;
        this.courseID = courseID;
    }

    public MtMixTrainOnlineSectionCourse() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getSection_id() {
        return section_id;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }
}
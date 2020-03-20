package com.elearning.pojo.courseStudy;

public class UserNeedLearnCourse extends UserNeedLearnCourseKey {
    private String trainName;

    private Byte isStationTrain;

    private String sectionName;

    private String courseName;

    private Double classHour;

    private String courseType;


    public UserNeedLearnCourse(Integer operatorId, Integer train_id, String year, Integer section_id, Long course_id, String trainName, Byte isStationTrain, String sectionName, String courseName, Double classHour, String courseType) {
        super(operatorId, train_id, year, section_id, course_id);
        this.trainName = trainName;
        this.isStationTrain = isStationTrain;
        this.sectionName = sectionName;
        this.courseName = courseName;
        this.classHour = classHour;
        this.courseType = courseType;
    }


    public UserNeedLearnCourse() {
        super();
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName == null ? null : trainName.trim();
    }

    public Byte getIsStationTrain() {
        return isStationTrain;
    }

    public void setIsStationTrain(Byte isStationTrain) {
        this.isStationTrain = isStationTrain;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName == null ? null : sectionName.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Double getClassHour() {
        return classHour;
    }

    public void setClassHour(Double classHour) {
        this.classHour = classHour;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType == null ? null : courseType.trim();
    }

}
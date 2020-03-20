package com.elearning.pojo.mixtraining;

import java.util.Date;

public class MtMixTrainSectionInfo {
    private Integer ID;

    private Integer programBasicInfoID;

    private Integer sectionNO;

    private String sectionName;

    private Integer sectionType;

    private Date endTime;

    private String location;

    private Date startTime;

    private Double classHour;

    private Integer arrangeID;

    public MtMixTrainSectionInfo(Integer ID, Integer programBasicInfoID, Integer sectionNO, String sectionName, Integer sectionType, Date endTime, String location, Date startTime, Double classHour, Integer arrangeID) {
        this.ID = ID;
        this.programBasicInfoID = programBasicInfoID;
        this.sectionNO = sectionNO;
        this.sectionName = sectionName;
        this.sectionType = sectionType;
        this.endTime = endTime;
        this.location = location;
        this.startTime = startTime;
        this.classHour = classHour;
        this.arrangeID = arrangeID;
    }

    public MtMixTrainSectionInfo() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getProgramBasicInfoID() {
        return programBasicInfoID;
    }

    public void setProgramBasicInfoID(Integer programBasicInfoID) {
        this.programBasicInfoID = programBasicInfoID;
    }

    public Integer getSectionNO() {
        return sectionNO;
    }

    public void setSectionNO(Integer sectionNO) {
        this.sectionNO = sectionNO;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName == null ? null : sectionName.trim();
    }

    public Integer getSectionType() {
        return sectionType;
    }

    public void setSectionType(Integer sectionType) {
        this.sectionType = sectionType;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Double getClassHour() {
        return classHour;
    }

    public void setClassHour(Double classHour) {
        this.classHour = classHour;
    }

    public Integer getArrangeID() {
        return arrangeID;
    }

    public void setArrangeID(Integer arrangeID) {
        this.arrangeID = arrangeID;
    }
}
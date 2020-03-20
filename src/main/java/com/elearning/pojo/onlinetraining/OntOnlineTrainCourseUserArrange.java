package com.elearning.pojo.onlinetraining;

import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;

import java.util.HashSet;
import java.util.Set;

public class OntOnlineTrainCourseUserArrange {
    private Integer ID;

    private String serialNO;

    private Integer trainID;

    private String arrangeName;

    private String year;

    private Set<Course> courseSet = new HashSet<>();

    private Set<EosOperator> eosoperatorSet = new HashSet<>();

    public OntOnlineTrainCourseUserArrange(Integer ID, String serialNO, Integer trainID, String arrangeName, String year) {
        this.ID = ID;
        this.serialNO = serialNO;
        this.trainID = trainID;
        this.arrangeName = arrangeName;
        this.year = year;
    }

    public OntOnlineTrainCourseUserArrange(String serialNO, Integer trainID,
                                           String arrangeName) {
        this.serialNO = serialNO;
        this.trainID = trainID;
        this.arrangeName = arrangeName;
    }
    public OntOnlineTrainCourseUserArrange() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getSerialNO() {
        return serialNO;
    }

    public void setSerialNO(String serialNO) {
        this.serialNO = serialNO == null ? null : serialNO.trim();
    }

    public Integer getTrainID() {
        return trainID;
    }

    public void setTrainID(Integer trainID) {
        this.trainID = trainID;
    }

    public String getArrangeName() {
        return arrangeName;
    }

    public void setArrangeName(String arrangeName) {
        this.arrangeName = arrangeName == null ? null : arrangeName.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Set<Course> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }

    public Set<EosOperator> getEosoperatorSet() {
        return eosoperatorSet;
    }

    public void setEosoperatorSet(Set<EosOperator> eosoperatorSet) {
        this.eosoperatorSet = eosoperatorSet;
    }
}
package com.elearning.vo.onlinetraining;
import com.elearning.annotation.SimpleDisplay;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;
/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/12/6 14:35
 */
public class BasicTrainForm {


    @SimpleDisplay
    private Long trainId;


    @SimpleDisplay
    private String trainName;

    @SimpleDisplay
    private String trainTypeName;

    @SimpleDisplay
    private Integer trainTypeId;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;

    @SimpleDisplay
    private String startTimeStr;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

    @SimpleDisplay
    private String endTimeStr;

    @SimpleDisplay
    private Integer tenantId;

    @SimpleDisplay
    private String tenantName;

    @SimpleDisplay
    private String sponsorName;

    @SimpleDisplay
    private String orgName;

    @SimpleDisplay
    private String organizerName;

    @SimpleDisplay
    private String telephone;

    @SimpleDisplay
    private String organizerEmail;

    @SimpleDisplay
    private Double classHour;

    @SimpleDisplay
    private Integer attendantCount;

    private Integer ifBJ;

    @SimpleDisplay
    private String location;

    @SimpleDisplay
    private String isEnrolledStr;

    private Short isEnrolled;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date programStartTime;

    @SimpleDisplay
    private String programStartTimeStr;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date programEndTime;

    @SimpleDisplay
    private String programEndTimeStr;

    private Integer itemType;

    @SimpleDisplay
    private String itemTypeStr;

    private Integer implStatusId;

    @SimpleDisplay
    private String implStatus;

    @SimpleDisplay
    private Integer reported;

    @SimpleDisplay
    private Integer approveStatus;

    @SimpleDisplay
    private String approveStatusName;


    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Long getTrainId() {
        return this.trainId;
    }


    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainTypeName() {
        return trainTypeName;
    }



    public Integer getTrainTypeId() {
        return trainTypeId;
    }

    public void setTrainTypeId(Integer trainTypeId) {
        this.trainTypeId = trainTypeId;
    }

    public void setTrainTypeName(String trainTypeName) {
        this.trainTypeName = trainTypeName;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public void setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail;
    }

    public Double getClassHour() {
        return classHour;
    }

    public void setClassHour(Double classHour) {
        this.classHour = classHour;
    }

    public Integer getAttendantCount() {
        return attendantCount;
    }

    public void setAttendantCount(Integer attendantCount) {
        this.attendantCount = attendantCount;
    }

    public Integer getIfBJ() {
        return ifBJ;
    }

    public void setIfBJ(Integer ifBJ) {
        this.ifBJ = ifBJ;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getIsEnrolledStr() {
        return isEnrolledStr;
    }

    public void setIsEnrolledStr(String isEnrolledStr) {
        this.isEnrolledStr = isEnrolledStr;
    }

    public Short getIsEnrolled() {
        return isEnrolled;
    }

    public void setIsEnrolled(Short isEnrolled) {
        this.isEnrolled = isEnrolled;
    }



    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public Date getProgramStartTime() {
        return programStartTime;
    }

    public void setProgramStartTime(Date programStartTime) {
        this.programStartTime = programStartTime;
    }

    public String getProgramStartTimeStr() {
        return programStartTimeStr;
    }

    public void setProgramStartTimeStr(String programStartTimeStr) {
        this.programStartTimeStr = programStartTimeStr;
    }

    public Date getProgramEndTime() {
        return programEndTime;
    }

    public void setProgramEndTime(Date programEndTime) {
        this.programEndTime = programEndTime;
    }

    public String getProgramEndTimeStr() {
        return programEndTimeStr;
    }

    public void setProgramEndTimeStr(String programEndTimeStr) {
        this.programEndTimeStr = programEndTimeStr;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getItemTypeStr() {
        return itemTypeStr;
    }

    public void setItemTypeStr(String itemTypeStr) {
        this.itemTypeStr = itemTypeStr;
    }

    public Integer getImplStatusId() {
        return implStatusId;
    }

    public void setImplStatusId(Integer implStatusId) {
        this.implStatusId = implStatusId;
    }

    public String getImplStatus() {
        return implStatus;
    }

    public void setImplStatus(String implStatus) {
        this.implStatus = implStatus;
    }

    public Integer getReported() {
        return reported;
    }

    public void setReported(Integer reported) {
        this.reported = reported;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveStatusName() {
        return approveStatusName;
    }

    public void setApproveStatusName(String approveStatusName) {
        this.approveStatusName = approveStatusName;
    }

}

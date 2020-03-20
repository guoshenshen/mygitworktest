package com.elearning.vo.teacher;

import com.elearning.pojo.pub.EosOperator;

import java.util.List;


public class TchrBaseInfoForm {

    private Integer teacherId;
    private Integer operatorId;
    private String teacherName;
    private String gender;
    private String birthDate;
    private Integer orgId;
    private Integer postId;
    private String degree;
    private String graduateSchool;
    private String majorSubject;
    private String telephone;
    private String email;
    private String teachDirection;
    private Byte formal;
    private Integer level;
    private Integer secretLevel;
    private Double teachHour;
    private Double satisfy;
    private Double cost;
    private String workPlace;
    private String post;
    private String title;
    private Integer score;
    private String relatedMaterailPath;
    private String relatedMaterailType;
    private String relatedMaterailSize;
    private String isUnderScope;
    private Byte isShared;

    private String employedStyle;
    private String relatedMaterailName;
    //private FormFile formFile;
    private String operatorFlag;
    private Integer tenantId;
    private Integer expertAreaId;
    private Integer openScope;
    private Integer sourceTeacherId;
    private String openStatus;
    private String hasStatus;
    private Integer subExpertAreaId;
    private String expertAreaName;
    private Integer updatable;
    private Integer isOpenTelephone;
    private Integer isOpenEmail;

    /**
     * 共享某课程的管理员序列
     */
    private String shareOperatorSeries;

    /**
     * 共享某课程的管理员队列
     */
    private List<EosOperator> shareOperators;

    private String inner;
    //private FormFile headPicFile;

    private String headPic;
    private Integer isNoted;
    private Integer isSendToIndex;
    private Integer isLinkSchedule;
    //private FormFile materialFile;

    private String operatorName;
    private String remark;
    private String keyWords;
    private String courseName;       //擅长课程  来自tchr_teacherusecourse
    private String courseWareName;   //相关课件  来自tchr_teacherusecourse


    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getMajorSubject() {
        return majorSubject;
    }

    public void setMajorSubject(String majorSubject) {
        this.majorSubject = majorSubject;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeachDirection() {
        return teachDirection;
    }

    public void setTeachDirection(String teachDirection) {
        this.teachDirection = teachDirection;
    }

    public Byte getFormal() {
        return formal;
    }

    public void setFormal(Byte formal) {
        this.formal = formal;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(Integer secretLevel) {
        this.secretLevel = secretLevel;
    }

    public Double getTeachHour() {
        return teachHour;
    }

    public void setTeachHour(Double teachHour) {
        this.teachHour = teachHour;
    }

    public Double getSatisfy() {
        return satisfy;
    }

    public void setSatisfy(Double satisfy) {
        this.satisfy = satisfy;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRelatedMaterailPath() {
        return relatedMaterailPath;
    }

    public void setRelatedMaterailPath(String relatedMaterailPath) {
        this.relatedMaterailPath = relatedMaterailPath;
    }

    public String getRelatedMaterailType() {
        return relatedMaterailType;
    }

    public void setRelatedMaterailType(String relatedMaterailType) {
        this.relatedMaterailType = relatedMaterailType;
    }

    public String getRelatedMaterailSize() {
        return relatedMaterailSize;
    }

    public void setRelatedMaterailSize(String relatedMaterailSize) {
        this.relatedMaterailSize = relatedMaterailSize;
    }

    public String getIsUnderScope() {
        return isUnderScope;
    }

    public void setIsUnderScope(String isUnderScope) {
        this.isUnderScope = isUnderScope;
    }

    public Byte getIsShared() {
        return isShared;
    }

    public void setIsShared(Byte isShared) {
        this.isShared = isShared;
    }

    public String getEmployedStyle() {
        return employedStyle;
    }

    public void setEmployedStyle(String employedStyle) {
        this.employedStyle = employedStyle;
    }

    public String getRelatedMaterailName() {
        return relatedMaterailName;
    }

    public void setRelatedMaterailName(String relatedMaterailName) {
        this.relatedMaterailName = relatedMaterailName;
    }

    public String getOperatorFlag() {
        return operatorFlag;
    }

    public void setOperatorFlag(String operatorFlag) {
        this.operatorFlag = operatorFlag;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getExpertAreaId() {
        return expertAreaId;
    }

    public void setExpertAreaId(Integer expertAreaId) {
        this.expertAreaId = expertAreaId;
    }

    public Integer getOpenScope() {
        return openScope;
    }

    public void setOpenScope(Integer openScope) {
        this.openScope = openScope;
    }

    public Integer getSourceTeacherId() {
        return sourceTeacherId;
    }

    public void setSourceTeacherId(Integer sourceTeacherId) {
        this.sourceTeacherId = sourceTeacherId;
    }

    public String getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(String openStatus) {
        this.openStatus = openStatus;
    }

    public String getHasStatus() {
        return hasStatus;
    }

    public void setHasStatus(String hasStatus) {
        this.hasStatus = hasStatus;
    }

    public Integer getSubExpertAreaId() {
        return subExpertAreaId;
    }

    public void setSubExpertAreaId(Integer subExpertAreaId) {
        this.subExpertAreaId = subExpertAreaId;
    }

    public String getExpertAreaName() {
        return expertAreaName;
    }

    public void setExpertAreaName(String expertAreaName) {
        this.expertAreaName = expertAreaName;
    }

    public Integer getUpdatable() {
        return updatable;
    }

    public void setUpdatable(Integer updatable) {
        this.updatable = updatable;
    }

    public Integer getIsOpenTelephone() {
        return isOpenTelephone;
    }

    public void setIsOpenTelephone(Integer isOpenTelephone) {
        this.isOpenTelephone = isOpenTelephone;
    }

    public Integer getIsOpenEmail() {
        return isOpenEmail;
    }

    public void setIsOpenEmail(Integer isOpenEmail) {
        this.isOpenEmail = isOpenEmail;
    }

    public String getShareOperatorSeries() {
        return shareOperatorSeries;
    }

    public void setShareOperatorSeries(String shareOperatorSeries) {
        this.shareOperatorSeries = shareOperatorSeries;
    }

    public List<EosOperator> getShareOperators() {
        return shareOperators;
    }

    public void setShareOperators(List<EosOperator> shareOperators) {
        this.shareOperators = shareOperators;
    }

    public String getInner() {
        return inner;
    }

    public void setInner(String inner) {
        this.inner = inner;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Integer getIsNoted() {
        return isNoted;
    }

    public void setIsNoted(Integer isNoted) {
        this.isNoted = isNoted;
    }

    public Integer getIsSendToIndex() {
        return isSendToIndex;
    }

    public void setIsSendToIndex(Integer isSendToIndex) {
        this.isSendToIndex = isSendToIndex;
    }

    public Integer getIsLinkSchedule() {
        return isLinkSchedule;
    }

    public void setIsLinkSchedule(Integer isLinkSchedule) {
        this.isLinkSchedule = isLinkSchedule;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseWareName() {
        return courseWareName;
    }

    public void setCourseWareName(String courseWareName) {
        this.courseWareName = courseWareName;
    }
}

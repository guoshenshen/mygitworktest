package com.elearning.pojo.teacher;

import java.util.Date;

public class TchrBaseInfo {
    private Integer id;

    private Integer operatorId;

    private String teacherName;

    private Integer gender;

    private Date birthDate;

    private Integer orgId;

    private Integer postID;

    private String degree;

    private String graduateSchool;

    private String majorSubject;

    private String telephone;

    private String email;

    private String teachDirection;

    private Boolean formal;

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

    private Integer isUnderScope;

    private Boolean isShared;

    private Date createDate;

    private String courseName;

    private Integer tenantId;

    private Integer sourceTeacherId;

    private Integer openScope;

    private Integer expertAreaId;

    private Integer subExpertAreaId;

    private String headPic;

    private Integer isNoted;

    private Integer isSendToIndex;

    private Integer isLinkSchedule;

    private String keyWords;

    private Integer hireStyle;

    private Integer isOpenTelephone;

    private Integer isOpenEmail;

    private String shareOperatorSeries;

    private String remark;

    public TchrBaseInfo(Integer id, Integer operatorId, String teacherName, Integer gender, Date birthDate, Integer orgId, Integer postID, String degree, String graduateSchool, String majorSubject, String telephone, String email, String teachDirection, Boolean formal, Integer level, Integer secretLevel, Double teachHour, Double satisfy, Double cost, String workPlace, String post, String title, Integer score, String relatedMaterailPath, String relatedMaterailType, String relatedMaterailSize, Integer isUnderScope, Boolean isShared, Date createDate, String courseName, Integer tenantId, Integer sourceTeacherId, Integer openScope, Integer expertAreaId, Integer subExpertAreaId, String headPic, Integer isNoted, Integer isSendToIndex, Integer isLinkSchedule, String keyWords, Integer hireStyle, Integer isOpenTelephone, Integer isOpenEmail, String shareOperatorSeries, String remark) {
        this.id = id;
        this.operatorId = operatorId;
        this.teacherName = teacherName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.orgId = orgId;
        this.postID = postID;
        this.degree = degree;
        this.graduateSchool = graduateSchool;
        this.majorSubject = majorSubject;
        this.telephone = telephone;
        this.email = email;
        this.teachDirection = teachDirection;
        this.formal = formal;
        this.level = level;
        this.secretLevel = secretLevel;
        this.teachHour = teachHour;
        this.satisfy = satisfy;
        this.cost = cost;
        this.workPlace = workPlace;
        this.post = post;
        this.title = title;
        this.score = score;
        this.relatedMaterailPath = relatedMaterailPath;
        this.relatedMaterailType = relatedMaterailType;
        this.relatedMaterailSize = relatedMaterailSize;
        this.isUnderScope = isUnderScope;
        this.isShared = isShared;
        this.createDate = createDate;
        this.courseName = courseName;
        this.tenantId = tenantId;
        this.sourceTeacherId = sourceTeacherId;
        this.openScope = openScope;
        this.expertAreaId = expertAreaId;
        this.subExpertAreaId = subExpertAreaId;
        this.headPic = headPic;
        this.isNoted = isNoted;
        this.isSendToIndex = isSendToIndex;
        this.isLinkSchedule = isLinkSchedule;
        this.keyWords = keyWords;
        this.hireStyle = hireStyle;
        this.isOpenTelephone = isOpenTelephone;
        this.isOpenEmail = isOpenEmail;
        this.shareOperatorSeries = shareOperatorSeries;
        this.remark = remark;
    }

    public TchrBaseInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool == null ? null : graduateSchool.trim();
    }

    public String getMajorSubject() {
        return majorSubject;
    }

    public void setMajorSubject(String majorSubject) {
        this.majorSubject = majorSubject == null ? null : majorSubject.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTeachDirection() {
        return teachDirection;
    }

    public void setTeachDirection(String teachDirection) {
        this.teachDirection = teachDirection == null ? null : teachDirection.trim();
    }

    public Boolean getFormal() {
        return formal;
    }

    public void setFormal(Boolean formal) {
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
        this.workPlace = workPlace == null ? null : workPlace.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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
        this.relatedMaterailPath = relatedMaterailPath == null ? null : relatedMaterailPath.trim();
    }

    public String getRelatedMaterailType() {
        return relatedMaterailType;
    }

    public void setRelatedMaterailType(String relatedMaterailType) {
        this.relatedMaterailType = relatedMaterailType == null ? null : relatedMaterailType.trim();
    }

    public String getRelatedMaterailSize() {
        return relatedMaterailSize;
    }

    public void setRelatedMaterailSize(String relatedMaterailSize) {
        this.relatedMaterailSize = relatedMaterailSize == null ? null : relatedMaterailSize.trim();
    }

    public Integer getIsUnderScope() {
        return isUnderScope;
    }

    public void setIsUnderScope(Integer isUnderScope) {
        this.isUnderScope = isUnderScope;
    }

    public Boolean getIsShared() {
        return isShared;
    }

    public void setIsShared(Boolean isShared) {
        this.isShared = isShared;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getSourceTeacherId() {
        return sourceTeacherId;
    }

    public void setSourceTeacherId(Integer sourceTeacherId) {
        this.sourceTeacherId = sourceTeacherId;
    }

    public Integer getOpenScope() {
        return openScope;
    }

    public void setOpenScope(Integer openScope) {
        this.openScope = openScope;
    }

    public Integer getExpertAreaId() {
        return expertAreaId;
    }

    public void setExpertAreaId(Integer expertAreaId) {
        this.expertAreaId = expertAreaId;
    }

    public Integer getSubExpertAreaId() {
        return subExpertAreaId;
    }

    public void setSubExpertAreaId(Integer subExpertAreaId) {
        this.subExpertAreaId = subExpertAreaId;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
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

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords == null ? null : keyWords.trim();
    }

    public Integer getHireStyle() {
        return hireStyle;
    }

    public void setHireStyle(Integer hireStyle) {
        this.hireStyle = hireStyle;
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
        this.shareOperatorSeries = shareOperatorSeries == null ? null : shareOperatorSeries.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
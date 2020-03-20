package com.elearning.vo.CourseStudy;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/22 14:09
 */
public class UserJoinedCourseForm {

    private Long operatorID;
    private long courseId;
    private String courseName;
    private String courseType;
    private String lateTime;
    private String studyProgress;
    private double classHour;
    private Integer trainId;
    private Integer sectionId;
    private Integer totalLearnedUser;
    private Integer discussUser;
    private String score;
    private String coursePic;
    private Integer courseStudyFlag ; //课程信息页面显示学习状态的标志。0：选学；1：开始学习；2：继续学习;3:已选课程
    private Integer courseArrangeID;
    private Integer totalStudyTime;
    private Integer totalStudyAmount;


    private String produceOrgName;
    private String creator;
    private String PictureURL;

    public Integer getTotalStudyAmount() {
        return totalStudyAmount;
    }
    public void setTotalStudyAmount(Integer totalStudyAmount) {
        this.totalStudyAmount = totalStudyAmount;
    }
    public Integer getTotalStudyTime() {
        return totalStudyTime;
    }
    public void setTotalStudyTime(Integer totalStudyTime) {
        this.totalStudyTime = totalStudyTime;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseType() {
        return courseType;
    }
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
    public String getLateTime() {
        return lateTime;
    }
    public void setLateTime(String lateTime) {
        this.lateTime = lateTime;
    }
    public Long getOperatorID() {
        return operatorID;
    }
    public void setOperatorID(Long operatorID) {
        this.operatorID = operatorID;
    }
    public String getStudyProgress() {
        return studyProgress;
    }
    public void setStudyProgress(String studyProgress) {
        this.studyProgress = studyProgress;
    }
    public double getClassHour() {
        return classHour;
    }
    public void setClassHour(double classHour) {
        this.classHour = classHour;
    }

    public Integer getDiscussUser() {
        return discussUser;
    }
    public void setDiscussUser(Integer discussUser) {
        this.discussUser = discussUser;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }
    public Integer getTotalLearnedUser() {
        return totalLearnedUser;
    }
    public void setTotalLearnedUser(Integer totalLearnedUser) {
        this.totalLearnedUser = totalLearnedUser;
    }
    public Integer getCourseStudyFlag() {
        return courseStudyFlag;
    }
    public void setCourseStudyFlag(Integer courseStudyFlag) {
        this.courseStudyFlag = courseStudyFlag;
    }
    public String getCoursePic() {
        return coursePic;
    }
    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }
    public Integer getCourseArrangeID() {
        return courseArrangeID;
    }
    public void setCourseArrangeID(Integer courseArrangeID) {
        this.courseArrangeID = courseArrangeID;
    }
    public String getProduceOrgName() {
        return produceOrgName;
    }
    public void setProduceOrgName(String produceOrgName) {
        this.produceOrgName = produceOrgName;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getPictureURL() {
        return PictureURL;
    }
    public void setPictureURL(String pictureURL) {
        PictureURL = pictureURL;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
}

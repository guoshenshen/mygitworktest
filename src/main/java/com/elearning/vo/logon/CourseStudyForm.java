package com.elearning.vo.logon;


import com.elearning.annotation.SimpleDisplay;
import com.elearning.common.CacheUtils;
import com.elearning.pojo.pub.Course;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CourseStudyForm {

	@SimpleDisplay
	private Long course_id;

	@SimpleDisplay
	private String courseName;

	@SimpleDisplay
	private String courseTypeName;

	private String content;

	@SimpleDisplay
	private Integer tenantId;

	@SimpleDisplay
	private String tenantName;

	@SimpleDisplay
	private String produceOrgName;

	private Integer orgId;

	@SimpleDisplay
	private Date upLoadDate;

	private Double studyTotalTime;

	private Long studyTotalCount;

	@SimpleDisplay
	private String pictureURL;

	@SimpleDisplay
	private Integer totalLearnedUser;

	private String score;


	@SimpleDisplay
	private String creator;//主讲人

	@SimpleDisplay
	private double classHour;

	@SimpleDisplay
	private Date shareDate;

	public Map<Integer, Integer> scoreMap = new HashMap<Integer, Integer>();

	Integer courseStudyFlag = 0; //课程信息页面显示学习状态的标志。0：选学；1：开始学习；2：继续学习;3:已选课程

	public CourseStudyForm() {

	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(this == obj) {
			return true;
		}
		if(obj instanceof CourseStudyForm) {
			CourseStudyForm form =(CourseStudyForm)obj;
			if(form.course_id.equals(this.course_id)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return course_id.hashCode();
	}

	public void setByCourse(Course course) {
		this.tenantId = course.getTenantId();
		this.tenantName = CacheUtils.getTenantName(this.tenantId);
		this.produceOrgName=course.getProduceOrgName();
		this.course_id = course.getCourseId();
		this.courseName = course.getCourseName();
		this.content = course.getContent();
		this.upLoadDate = course.getCreateDate();
		this.pictureURL = course.getPictureURL();
		if (course.getScore() != null) {
			this.score = course.getScore().toString();
		}
		this.creator = course.getCreator();
		this.produceOrgName=course.getProduceOrgName();
		this.orgId=course.getOrgId();
		this.classHour = course.getClassHour();
	}

	public CourseStudyForm(Course course) {

		this.tenantId = course.getTenantId();
		this.tenantName = CacheUtils.getTenantName(this.tenantId);
		this.course_id = course.getCourseId();
		this.courseName = course.getCourseName();
		//this.courseTypeName = course.getcourse;
		this.content = course.getContent();
		this.upLoadDate = course.getCreateDate();
		//	this.studyTotalTime = course.;
		//	this.studyTotalCount = studyTotalCount;
		this.pictureURL = course.getPictureURL();
		//	this.totalLearnedUser = course.gett;
		if (course.getScore() != null) {
			this.score = course.getScore().toString();
		}
		this.setProduceOrgName(course.getProduceOrgName());
		this.setOrgId(course.getOrgId());
		this.creator = course.getCreator();
		this.classHour = course.getClassHour();
		//this.courseStudyFlag = course;
	}

	public long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseTypeName() {
		return courseTypeName;
	}
	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public Double getStudyTotalTime() {
		return studyTotalTime;
	}
	public void setStudyTotalTime(Double studyTotalTime) {
		this.studyTotalTime = studyTotalTime;
	}
	public Date getUpLoadDate() {
		return upLoadDate;
	}
	public void setUpLoadDate(Date upLoadDate) {
		this.upLoadDate = upLoadDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getStudyTotalCount() {
		return studyTotalCount;
	}
	public void setStudyTotalCount(Long studyTotalCount) {
		this.studyTotalCount = studyTotalCount;
	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public double getClassHour() {
		return classHour;
	}
	public void setClassHour(double classHour) {
		this.classHour = classHour;
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

	public String getProduceOrgName() {
		return produceOrgName;
	}

	public void setProduceOrgName(String produceOrgName) {
		this.produceOrgName = produceOrgName;
	}

	public Map<Integer, Integer> getScoreMap() {
		return scoreMap;
	}

	public void setScoreMap(Map<Integer, Integer> scoreMap) {
		this.scoreMap = scoreMap;
	}

	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
}

package com.elearning.vo.message;

public class MsgMessageArrangeUserForm {
	
	private Integer id;
	
	private Integer msgId;
	private String title;
	private Integer attendable;
	private Integer userId;
	private String operatorName;
	private String comment;
	private Integer trainId;
	private String sendbackTime;
	private String orgName;
	private String parentOrgName;
	
	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAttendable() {
		return attendable;
	}

	public void setAttendable(Integer attendable) {
		this.attendable = attendable;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSendbackTime() {
		return sendbackTime;
	}

	public void setSendbackTime(String sendbackTime) {
		this.sendbackTime = sendbackTime;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}
	

}

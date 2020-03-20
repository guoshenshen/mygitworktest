package com.elearning.pojo.courseStudy;

public class UcsUserScoInfo {
    private Integer ID;

    private Integer userID;

    private Long courseId;

    private String SCOID;

    private String AUNCH;

    private String PARAMETERSTRING;

    private String LESSONSTATUS;

    private String PREREQUISITES;

    private String EXITCOURSE;

    private String ENTRY;

    private String SESSIONTIME;

    private String TOTALTIME;

    private String MASTERYSCORE;

    private Integer SEQUENCE;

    private String TYPE;

    private String LESSONLOCATION;

    private String year;

    public UcsUserScoInfo(Integer ID, Integer userID, Long courseId, String SCOID, String AUNCH, String PARAMETERSTRING, String LESSONSTATUS, String PREREQUISITES, String EXITCOURSE, String ENTRY, String SESSIONTIME, String TOTALTIME, String MASTERYSCORE, Integer SEQUENCE, String TYPE, String LESSONLOCATION, String year) {
        this.ID = ID;
        this.userID = userID;
        this.courseId = courseId;
        this.SCOID = SCOID;
        this.AUNCH = AUNCH;
        this.PARAMETERSTRING = PARAMETERSTRING;
        this.LESSONSTATUS = LESSONSTATUS;
        this.PREREQUISITES = PREREQUISITES;
        this.EXITCOURSE = EXITCOURSE;
        this.ENTRY = ENTRY;
        this.SESSIONTIME = SESSIONTIME;
        this.TOTALTIME = TOTALTIME;
        this.MASTERYSCORE = MASTERYSCORE;
        this.SEQUENCE = SEQUENCE;
        this.TYPE = TYPE;
        this.LESSONLOCATION = LESSONLOCATION;
        this.year = year;
    }

    public UcsUserScoInfo() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getSCOID() {
        return SCOID;
    }

    public void setSCOID(String SCOID) {
        this.SCOID = SCOID == null ? null : SCOID.trim();
    }

    public String getAUNCH() {
        return AUNCH;
    }

    public void setAUNCH(String AUNCH) {
        this.AUNCH = AUNCH == null ? null : AUNCH.trim();
    }

    public String getPARAMETERSTRING() {
        return PARAMETERSTRING;
    }

    public void setPARAMETERSTRING(String PARAMETERSTRING) {
        this.PARAMETERSTRING = PARAMETERSTRING == null ? null : PARAMETERSTRING.trim();
    }

    public String getLESSONSTATUS() {
        return LESSONSTATUS;
    }

    public void setLESSONSTATUS(String LESSONSTATUS) {
        this.LESSONSTATUS = LESSONSTATUS == null ? null : LESSONSTATUS.trim();
    }

    public String getPREREQUISITES() {
        return PREREQUISITES;
    }

    public void setPREREQUISITES(String PREREQUISITES) {
        this.PREREQUISITES = PREREQUISITES == null ? null : PREREQUISITES.trim();
    }

    public String getEXITCOURSE() {
        return EXITCOURSE;
    }

    public void setEXITCOURSE(String EXITCOURSE) {
        this.EXITCOURSE = EXITCOURSE == null ? null : EXITCOURSE.trim();
    }

    public String getENTRY() {
        return ENTRY;
    }

    public void setENTRY(String ENTRY) {
        this.ENTRY = ENTRY == null ? null : ENTRY.trim();
    }

    public String getSESSIONTIME() {
        return SESSIONTIME;
    }

    public void setSESSIONTIME(String SESSIONTIME) {
        this.SESSIONTIME = SESSIONTIME == null ? null : SESSIONTIME.trim();
    }

    public String getTOTALTIME() {
        return TOTALTIME;
    }

    public void setTOTALTIME(String TOTALTIME) {
        this.TOTALTIME = TOTALTIME == null ? null : TOTALTIME.trim();
    }

    public String getMASTERYSCORE() {
        return MASTERYSCORE;
    }

    public void setMASTERYSCORE(String MASTERYSCORE) {
        this.MASTERYSCORE = MASTERYSCORE == null ? null : MASTERYSCORE.trim();
    }

    public Integer getSEQUENCE() {
        return SEQUENCE;
    }

    public void setSEQUENCE(Integer SEQUENCE) {
        this.SEQUENCE = SEQUENCE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE == null ? null : TYPE.trim();
    }

    public String getLESSONLOCATION() {
        return LESSONLOCATION;
    }

    public void setLESSONLOCATION(String LESSONLOCATION) {
        this.LESSONLOCATION = LESSONLOCATION == null ? null : LESSONLOCATION.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }
}
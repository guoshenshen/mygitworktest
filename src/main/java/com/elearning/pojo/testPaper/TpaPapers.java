package com.elearning.pojo.testPaper;

public class TpaPapers {
    private Integer ID;

    private String paperTitle;

    private Integer paperTypeId;

    private Integer fullScore;

    private String paperDesc;

    private Long classifyId;

    private Integer classifySign;

    private Integer operatorID;

    private Integer strategyId;

    private Integer strategyType;

    private String classifyNumber="";

    private String classifyName="";

    public String getClassifyNumber() {
        return classifyNumber;
    }

    public void setClassifyNumber(String classifyNumber) {
        this.classifyNumber = classifyNumber;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public TpaPapers(Integer ID, String paperTitle, Integer paperTypeId, Integer fullScore, String paperDesc, Long classifyId, Integer classifySign, Integer operatorID, Integer strategyId, Integer strategyType) {
        this.ID = ID;
        this.paperTitle = paperTitle;
        this.paperTypeId = paperTypeId;
        this.fullScore = fullScore;
        this.paperDesc = paperDesc;
        this.classifyId = classifyId;
        this.classifySign = classifySign;
        this.operatorID = operatorID;
        this.strategyId = strategyId;
        this.strategyType = strategyType;
    }

    public TpaPapers() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle == null ? null : paperTitle.trim();
    }

    public Integer getPaperTypeId() {
        return paperTypeId;
    }

    public void setPaperTypeId(Integer paperTypeId) {
        this.paperTypeId = paperTypeId;
    }

    public Integer getFullScore() {
        return fullScore;
    }

    public void setFullScore(Integer fullScore) {
        this.fullScore = fullScore;
    }

    public String getPaperDesc() {
        return paperDesc;
    }

    public void setPaperDesc(String paperDesc) {
        this.paperDesc = paperDesc == null ? null : paperDesc.trim();
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getClassifySign() {
        return classifySign;
    }

    public void setClassifySign(Integer classifySign) {
        this.classifySign = classifySign;
    }

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(Integer strategyType) {
        this.strategyType = strategyType;
    }
}
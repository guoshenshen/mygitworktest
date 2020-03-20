package com.elearning.pojo.testQuestions;

public class TqQuestions {
    private Integer ID;

    private Integer qustioinTypeID;

    private Integer knowPoint;

    private Double qScore;

    private String qContent;

    private String qAnswer;

    private String qAnalyze;

    private Long classifyId;

    private Integer classifySign;

    private Integer operatorID;

    public TqQuestions(Integer ID, Integer qustioinTypeID, Integer knowPoint, Double qScore, String qContent, String qAnswer, String qAnalyze, Long classifyId, Integer classifySign, Integer operatorID) {
        this.ID = ID;
        this.qustioinTypeID = qustioinTypeID;
        this.knowPoint = knowPoint;
        this.qScore = qScore;
        this.qContent = qContent;
        this.qAnswer = qAnswer;
        this.qAnalyze = qAnalyze;
        this.classifyId = classifyId;
        this.classifySign = classifySign;
        this.operatorID = operatorID;
    }

    public TqQuestions() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getQustioinTypeID() {
        return qustioinTypeID;
    }

    public void setQustioinTypeID(Integer qustioinTypeID) {
        this.qustioinTypeID = qustioinTypeID;
    }

    public Integer getKnowPoint() {
        return knowPoint;
    }

    public void setKnowPoint(Integer knowPoint) {
        this.knowPoint = knowPoint;
    }

    public Double getqScore() {
        return qScore;
    }

    public void setqScore(Double qScore) {
        this.qScore = qScore;
    }

    public String getqContent() {
        return qContent;
    }

    public void setqContent(String qContent) {
        this.qContent = qContent == null ? null : qContent.trim();
    }

    public String getqAnswer() {
        return qAnswer;
    }

    public void setqAnswer(String qAnswer) {
        this.qAnswer = qAnswer == null ? null : qAnswer.trim();
    }

    public String getqAnalyze() {
        return qAnalyze;
    }

    public void setqAnalyze(String qAnalyze) {
        this.qAnalyze = qAnalyze == null ? null : qAnalyze.trim();
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
}
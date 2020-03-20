package com.elearning.pojo.testPaper;

import com.elearning.pojo.pub.EosOperator;

import java.util.ArrayList;
import java.util.List;

public class TpaQuestions {
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

    //--------------------------继续教育网中关联的一些表-----------------------------------------

    EosOperator user;

    //private Set<TpaQuestionItem> items = new HashSet<>();
    private List<TpaQuestionItem> items = new ArrayList<>();

    private String classifyNumber="";
    private String classifyName="";


    private String userAnswer;
    private Double userrealScore;
    private int answerPeopleCount;
    private Double correctPercent;
    private Double errorPercent;

    //-------------------------------------------------------------------

    public EosOperator getUser() {
        return user;
    }

    public void setUser(EosOperator user) {
        this.user = user;
    }

    public List<TpaQuestionItem> getItems() {
        return items;
    }

    public void setItems(List<TpaQuestionItem> items) {
        this.items = items;
    }

    /*public Set<TpaQuestionItem> getItems() {
        return items;
    }

    public void setItems(Set<TpaQuestionItem> items) {
        this.items = items;
    }*/

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

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Double getUserrealScore() {
        return userrealScore;
    }

    public void setUserrealScore(Double userrealScore) {
        this.userrealScore = userrealScore;
    }

    public int getAnswerPeopleCount() {
        return answerPeopleCount;
    }

    public void setAnswerPeopleCount(int answerPeopleCount) {
        this.answerPeopleCount = answerPeopleCount;
    }

    public Double getCorrectPercent() {
        return correctPercent;
    }

    public void setCorrectPercent(Double correctPercent) {
        this.correctPercent = correctPercent;
    }

    public Double getErrorPercent() {
        return errorPercent;
    }

    public void setErrorPercent(Double errorPercent) {
        this.errorPercent = errorPercent;
    }

    public TpaQuestions(Integer ID, Integer qustioinTypeID, Integer knowPoint, Double qScore, String qContent, String qAnswer, String qAnalyze, Long classifyId, Integer classifySign, Integer operatorID) {
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

    public TpaQuestions() {
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
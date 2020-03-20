package com.elearning.pojo.examManage;

public class ExamResult {
    private Integer id;

    private Integer operatorId;

    private Integer examId;

    private Integer paperID;

    private Integer questionID;

    private Double realScore;

    private Integer questionOrder;

    private Integer replyId;

    private String answer;

    public ExamResult(Integer id, Integer operatorId, Integer examId, Integer paperID, Integer questionID, Double realScore, Integer questionOrder, Integer replyId, String answer) {
        this.id = id;
        this.operatorId = operatorId;
        this.examId = examId;
        this.paperID = paperID;
        this.questionID = questionID;
        this.realScore = realScore;
        this.questionOrder = questionOrder;
        this.replyId = replyId;
        this.answer = answer;
    }

    public ExamResult() {
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

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getPaperID() {
        return paperID;
    }

    public void setPaperID(Integer paperID) {
        this.paperID = paperID;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public Double getRealScore() {
        return realScore;
    }

    public void setRealScore(Double realScore) {
        this.realScore = realScore;
    }

    public Integer getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }
}
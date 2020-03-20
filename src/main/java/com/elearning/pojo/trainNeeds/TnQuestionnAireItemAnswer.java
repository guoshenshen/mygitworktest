package com.elearning.pojo.trainNeeds;

public class TnQuestionnAireItemAnswer {
    private Integer ID;

    private Integer itemID;

    private Integer qn_id;

    private String otherAnswer;

    private String answerID;

    private String answer;

    public TnQuestionnAireItemAnswer(Integer ID, Integer itemID, Integer qn_id, String otherAnswer, String answerID, String answer) {
        this.ID = ID;
        this.itemID = itemID;
        this.qn_id = qn_id;
        this.otherAnswer = otherAnswer;
        this.answerID = answerID;
        this.answer = answer;
    }

    public TnQuestionnAireItemAnswer() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public Integer getQn_id() {
        return qn_id;
    }

    public void setQn_id(Integer qn_id) {
        this.qn_id = qn_id;
    }

    public String getOtherAnswer() {
        return otherAnswer;
    }

    public void setOtherAnswer(String otherAnswer) {
        this.otherAnswer = otherAnswer == null ? null : otherAnswer.trim();
    }

    public String getAnswerID() {
        return answerID;
    }

    public void setAnswerID(String answerID) {
        this.answerID = answerID == null ? null : answerID.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }
}
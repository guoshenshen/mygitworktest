package com.elearning.pojo.trainEva;

public class TeEvaQuestionnaireItemAnswer {
    private Integer ID;

    private Integer itemID;

    private String answer;

    private Integer qn_id;

    public TeEvaQuestionnaireItemAnswer(Integer ID, Integer itemID, String answer, Integer qn_id) {
        this.ID = ID;
        this.itemID = itemID;
        this.answer = answer;
        this.qn_id = qn_id;
    }

    public TeEvaQuestionnaireItemAnswer() {
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getQn_id() {
        return qn_id;
    }

    public void setQn_id(Integer qn_id) {
        this.qn_id = qn_id;
    }
}
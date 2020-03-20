package com.elearning.pojo.trainEva;

public class TeEvaQuestionnaireItemChoice {
    private Integer ID;

    private Integer itemID;

    private Integer choiceNO;

    private String choiceContent;

    public TeEvaQuestionnaireItemChoice(Integer ID, Integer itemID, Integer choiceNO, String choiceContent) {
        this.ID = ID;
        this.itemID = itemID;
        this.choiceNO = choiceNO;
        this.choiceContent = choiceContent;
    }

    public TeEvaQuestionnaireItemChoice() {
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

    public Integer getChoiceNO() {
        return choiceNO;
    }

    public void setChoiceNO(Integer choiceNO) {
        this.choiceNO = choiceNO;
    }

    public String getChoiceContent() {
        return choiceContent;
    }

    public void setChoiceContent(String choiceContent) {
        this.choiceContent = choiceContent == null ? null : choiceContent.trim();
    }
}
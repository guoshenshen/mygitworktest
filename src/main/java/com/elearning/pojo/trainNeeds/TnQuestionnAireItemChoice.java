package com.elearning.pojo.trainNeeds;

public class TnQuestionnAireItemChoice {
    private Integer ID;

    private Integer itemID;

    private Integer choiceNO;

    private String choiceContent;

    private Integer allowOtherInput;

    private String choiceCode;

    public TnQuestionnAireItemChoice(Integer ID, Integer itemID, Integer choiceNO, String choiceContent, Integer allowOtherInput, String choiceCode) {
        this.ID = ID;
        this.itemID = itemID;
        this.choiceNO = choiceNO;
        this.choiceContent = choiceContent;
        this.allowOtherInput = allowOtherInput;
        this.choiceCode = choiceCode;
    }

    public TnQuestionnAireItemChoice() {
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

    public Integer getAllowOtherInput() {
        return allowOtherInput;
    }

    public void setAllowOtherInput(Integer allowOtherInput) {
        this.allowOtherInput = allowOtherInput;
    }

    public String getChoiceCode() {
        return choiceCode;
    }

    public void setChoiceCode(String choiceCode) {
        this.choiceCode = choiceCode == null ? null : choiceCode.trim();
    }
}
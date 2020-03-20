package com.elearning.pojo.trainNeeds;

public class TnQuestionnAireItem {
    private Integer ID;

    private Integer basicInfoID;

    private Integer typeID;

    private Integer questionNO;

    private String title;

    public TnQuestionnAireItem(Integer ID, Integer basicInfoID, Integer typeID, Integer questionNO, String title) {
        this.ID = ID;
        this.basicInfoID = basicInfoID;
        this.typeID = typeID;
        this.questionNO = questionNO;
        this.title = title;
    }

    public TnQuestionnAireItem() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getBasicInfoID() {
        return basicInfoID;
    }

    public void setBasicInfoID(Integer basicInfoID) {
        this.basicInfoID = basicInfoID;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public Integer getQuestionNO() {
        return questionNO;
    }

    public void setQuestionNO(Integer questionNO) {
        this.questionNO = questionNO;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}
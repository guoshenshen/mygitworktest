package com.elearning.pojo.trainEva;

public class TeEvaQuestionnaireItem {
    private Integer ID;

    private Integer evaID;

    private Integer typeID;

    private Integer questionNO;

    private String title;

    public TeEvaQuestionnaireItem(Integer ID, Integer evaID, Integer typeID, Integer questionNO, String title) {
        this.ID = ID;
        this.evaID = evaID;
        this.typeID = typeID;
        this.questionNO = questionNO;
        this.title = title;
    }

    public TeEvaQuestionnaireItem() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getEvaID() {
        return evaID;
    }

    public void setEvaID(Integer evaID) {
        this.evaID = evaID;
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
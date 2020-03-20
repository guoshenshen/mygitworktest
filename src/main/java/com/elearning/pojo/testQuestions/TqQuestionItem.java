package com.elearning.pojo.testQuestions;

public class TqQuestionItem {
    private Integer ID;

    private Integer question_ID;

    private String qItemContent;

    private String qItem_name;

    public TqQuestionItem(Integer ID, Integer question_ID, String qItemContent, String qItem_name) {
        this.ID = ID;
        this.question_ID = question_ID;
        this.qItemContent = qItemContent;
        this.qItem_name = qItem_name;
    }

    public TqQuestionItem() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getQuestion_ID() {
        return question_ID;
    }

    public void setQuestion_ID(Integer question_ID) {
        this.question_ID = question_ID;
    }

    public String getqItemContent() {
        return qItemContent;
    }

    public void setqItemContent(String qItemContent) {
        this.qItemContent = qItemContent == null ? null : qItemContent.trim();
    }

    public String getqItem_name() {
        return qItem_name;
    }

    public void setqItem_name(String qItem_name) {
        this.qItem_name = qItem_name == null ? null : qItem_name.trim();
    }
}
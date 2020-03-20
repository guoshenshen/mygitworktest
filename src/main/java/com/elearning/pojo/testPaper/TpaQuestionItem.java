package com.elearning.pojo.testPaper;

import java.util.Objects;

public class TpaQuestionItem {

    private Integer ID;

    private Integer question_ID;

    private String qItemContent;

    private String qItem_name;

    public TpaQuestionItem(Integer ID, Integer question_ID, String qItemContent, String qItem_name) {
        this.ID = ID;
        this.question_ID = question_ID;
        this.qItemContent = qItemContent;
        this.qItem_name = qItem_name;
    }

    public TpaQuestionItem() {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TpaQuestionItem that = (TpaQuestionItem) o;
        return Objects.equals(ID, that.ID) &&
                Objects.equals(question_ID, that.question_ID) &&
                Objects.equals(qItemContent, that.qItemContent) &&
                Objects.equals(qItem_name, that.qItem_name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID, question_ID, qItemContent, qItem_name);
    }
}
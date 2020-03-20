package com.elearning.pojo.examManage;

public class ExamExamPapers {
    private Integer ID;

    private Integer examID;

    private Integer paperID;

    private String comment;

    public ExamExamPapers(Integer ID, Integer examID, Integer paperID, String comment) {
        this.ID = ID;
        this.examID = examID;
        this.paperID = paperID;
        this.comment = comment;
    }

    public ExamExamPapers() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getExamID() {
        return examID;
    }

    public void setExamID(Integer examID) {
        this.examID = examID;
    }

    public Integer getPaperID() {
        return paperID;
    }

    public void setPaperID(Integer paperID) {
        this.paperID = paperID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}
package com.elearning.pojo.testPaper;

public class TpaPaperQuestions {
    private Integer ID;

    private Integer paper_id;

    private Integer question_id;

    private Integer questionSequence;

    private Double questionMark;

    private TpaQuestions tpaQuestions;

    public TpaQuestions getTpaQuestions() {
        return tpaQuestions;
    }

    public void setTpaQuestions(TpaQuestions tpaQuestions) {
        this.tpaQuestions = tpaQuestions;
    }

    public TpaPaperQuestions(Integer ID, Integer paper_id, Integer question_id, Integer questionSequence, Double questionMark) {
        this.ID = ID;
        this.paper_id = paper_id;
        this.question_id = question_id;
        this.questionSequence = questionSequence;
        this.questionMark = questionMark;
    }

    public TpaPaperQuestions() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public Integer getQuestionSequence() {
        return questionSequence;
    }

    public void setQuestionSequence(Integer questionSequence) {
        this.questionSequence = questionSequence;
    }

    public Double getQuestionMark() {
        return questionMark;
    }

    public void setQuestionMark(Double questionMark) {
        this.questionMark = questionMark;
    }
}
package com.elearning.pojo.examManage;

public class ExamPaperScoreAnalyze {
    private Integer ID;

    private Integer examID;

    private Double minScore;

    private Double maxScore;

    public ExamPaperScoreAnalyze(Integer ID, Integer examID, Double minScore, Double maxScore) {
        this.ID = ID;
        this.examID = examID;
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public ExamPaperScoreAnalyze() {
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

    public Double getMinScore() {
        return minScore;
    }

    public void setMinScore(Double minScore) {
        this.minScore = minScore;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }
}
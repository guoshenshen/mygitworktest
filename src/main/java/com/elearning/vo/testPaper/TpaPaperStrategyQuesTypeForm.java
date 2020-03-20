package com.elearning.vo.testPaper;

import com.elearning.pojo.testPaper.TpaQuestions;

import java.util.List;

public class TpaPaperStrategyQuesTypeForm {

    private Integer strategyQuesNum;

    private Integer strategyQuesType;

    private String strategyQuesTypeName;

    private Integer strategyQuesScore;

    private Integer perStrategyQuesScore;

    private List<TpaQuestions> questionList;

    private String memo;

    private Integer strategyQuesTotalNum;

    private Integer strategyQuesSeq;

    public Integer getStrategyQuesNum() {
        return strategyQuesNum;
    }

    public void setStrategyQuesNum(Integer strategyQuesNum) {
        this.strategyQuesNum = strategyQuesNum;
    }

    public Integer getStrategyQuesType() {
        return strategyQuesType;
    }

    public void setStrategyQuesType(Integer strategyQuesType) {
        this.strategyQuesType = strategyQuesType;
    }

    public String getStrategyQuesTypeName() {
        return strategyQuesTypeName;
    }

    public void setStrategyQuesTypeName(String strategyQuesTypeName) {
        this.strategyQuesTypeName = strategyQuesTypeName;
    }

    public Integer getStrategyQuesScore() {
        return strategyQuesScore;
    }

    public void setStrategyQuesScore(Integer strategyQuesScore) {
        this.strategyQuesScore = strategyQuesScore;
    }

    public Integer getPerStrategyQuesScore() {
        return perStrategyQuesScore;
    }

    public void setPerStrategyQuesScore(Integer perStrategyQuesScore) {
        this.perStrategyQuesScore = perStrategyQuesScore;
    }

    public List<TpaQuestions> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<TpaQuestions> questionList) {
        this.questionList = questionList;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getStrategyQuesTotalNum() {
        return strategyQuesTotalNum;
    }

    public void setStrategyQuesTotalNum(Integer strategyQuesTotalNum) {
        this.strategyQuesTotalNum = strategyQuesTotalNum;
    }

    public Integer getStrategyQuesSeq() {
        return strategyQuesSeq;
    }

    public void setStrategyQuesSeq(Integer strategyQuesSeq) {
        this.strategyQuesSeq = strategyQuesSeq;
    }
}

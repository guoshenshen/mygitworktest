package com.elearning.pojo.testPaper;

public class TpaPaperStrategyQuesType {
    private Integer ID;

    private Integer strategyId;

    private Integer strategyQuesType;

    private Integer strategyQuesSeq;

    private Integer strategyQuesNum;

    private Integer strategyQuesScore;

    public TpaPaperStrategyQuesType(Integer ID, Integer strategyId, Integer strategyQuesType, Integer strategyQuesSeq, Integer strategyQuesNum, Integer strategyQuesScore) {
        this.ID = ID;
        this.strategyId = strategyId;
        this.strategyQuesType = strategyQuesType;
        this.strategyQuesSeq = strategyQuesSeq;
        this.strategyQuesNum = strategyQuesNum;
        this.strategyQuesScore = strategyQuesScore;
    }

    public TpaPaperStrategyQuesType() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getStrategyQuesType() {
        return strategyQuesType;
    }

    public void setStrategyQuesType(Integer strategyQuesType) {
        this.strategyQuesType = strategyQuesType;
    }

    public Integer getStrategyQuesSeq() {
        return strategyQuesSeq;
    }

    public void setStrategyQuesSeq(Integer strategyQuesSeq) {
        this.strategyQuesSeq = strategyQuesSeq;
    }

    public Integer getStrategyQuesNum() {
        return strategyQuesNum;
    }

    public void setStrategyQuesNum(Integer strategyQuesNum) {
        this.strategyQuesNum = strategyQuesNum;
    }

    public Integer getStrategyQuesScore() {
        return strategyQuesScore;
    }

    public void setStrategyQuesScore(Integer strategyQuesScore) {
        this.strategyQuesScore = strategyQuesScore;
    }
}
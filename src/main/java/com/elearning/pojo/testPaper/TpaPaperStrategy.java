package com.elearning.pojo.testPaper;

import java.util.Date;

public class TpaPaperStrategy {
    private Integer ID;

    private String strategyName;

    private Integer strategyFullScore;

    private Integer strategyType;

    private Date strategyCreateTime;

    private Integer strategyCreator;

    private Long strategyClassifyId;

    public TpaPaperStrategy(Integer ID, String strategyName, Integer strategyFullScore, Integer strategyType, Date strategyCreateTime, Integer strategyCreator, Long strategyClassifyId) {
        this.ID = ID;
        this.strategyName = strategyName;
        this.strategyFullScore = strategyFullScore;
        this.strategyType = strategyType;
        this.strategyCreateTime = strategyCreateTime;
        this.strategyCreator = strategyCreator;
        this.strategyClassifyId = strategyClassifyId;
    }

    public TpaPaperStrategy() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName == null ? null : strategyName.trim();
    }

    public Integer getStrategyFullScore() {
        return strategyFullScore;
    }

    public void setStrategyFullScore(Integer strategyFullScore) {
        this.strategyFullScore = strategyFullScore;
    }

    public Integer getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(Integer strategyType) {
        this.strategyType = strategyType;
    }

    public Date getStrategyCreateTime() {
        return strategyCreateTime;
    }

    public void setStrategyCreateTime(Date strategyCreateTime) {
        this.strategyCreateTime = strategyCreateTime;
    }

    public Integer getStrategyCreator() {
        return strategyCreator;
    }

    public void setStrategyCreator(Integer strategyCreator) {
        this.strategyCreator = strategyCreator;
    }

    public Long getStrategyClassifyId() {
        return strategyClassifyId;
    }

    public void setStrategyClassifyId(Long strategyClassifyId) {
        this.strategyClassifyId = strategyClassifyId;
    }
}
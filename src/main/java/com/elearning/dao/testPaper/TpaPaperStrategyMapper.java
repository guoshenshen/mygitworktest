package com.elearning.dao.testPaper;

import com.elearning.pojo.testPaper.TpaPaperStrategy;

public interface TpaPaperStrategyMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(TpaPaperStrategy record);

    int insertSelective(TpaPaperStrategy record);

    TpaPaperStrategy selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TpaPaperStrategy record);

    int updateByPrimaryKey(TpaPaperStrategy record);
}
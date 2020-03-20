package com.elearning.dao.selfStudy;

import com.elearning.pojo.selfStudy.SlPortfolio;

public interface SlPortfolioMapper {
    int deleteByPrimaryKey(Integer portfolioId);

    int insert(SlPortfolio record);

    int insertSelective(SlPortfolio record);

    SlPortfolio selectByPrimaryKey(Integer portfolioId);

    int updateByPrimaryKeySelective(SlPortfolio record);

    int updateByPrimaryKey(SlPortfolio record);
}
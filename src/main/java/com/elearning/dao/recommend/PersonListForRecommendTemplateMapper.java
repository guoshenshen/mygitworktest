package com.elearning.dao.recommend;

import com.elearning.pojo.recommend.PersonListForRecommendTemplate;

public interface PersonListForRecommendTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonListForRecommendTemplate record);

    int insertSelective(PersonListForRecommendTemplate record);

    PersonListForRecommendTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonListForRecommendTemplate record);

    int updateByPrimaryKey(PersonListForRecommendTemplate record);
}
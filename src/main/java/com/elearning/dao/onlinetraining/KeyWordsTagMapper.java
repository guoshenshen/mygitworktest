package com.elearning.dao.onlinetraining;

import com.elearning.pojo.onlinetraining.KeyWordsTag;

import java.util.List;

public interface KeyWordsTagMapper {
    int deleteByPrimaryKey(Integer tagID);

    int insert(KeyWordsTag record);

    int insertSelective(KeyWordsTag record);

    KeyWordsTag selectByPrimaryKey(Integer tagID);

    int updateByPrimaryKeySelective(KeyWordsTag record);

    int updateByPrimaryKey(KeyWordsTag record);

    List<KeyWordsTag> selectByKeyWords(String keyWords);
}
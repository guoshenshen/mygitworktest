package com.elearning.dao.selfStudy;

import com.elearning.pojo.selfStudy.SlNote;

public interface SlNoteMapper {
    int deleteByPrimaryKey(Integer slNoteId);

    int insert(SlNote record);

    int insertSelective(SlNote record);

    SlNote selectByPrimaryKey(Integer slNoteId);

    int updateByPrimaryKeySelective(SlNote record);

    int updateByPrimaryKey(SlNote record);
}
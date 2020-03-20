package com.elearning.dao.forum;

import com.elearning.pojo.forum.FrmBoard;

public interface FrmBoardMapper {
    int deleteByPrimaryKey(Integer boardId);

    int insert(FrmBoard record);

    int insertSelective(FrmBoard record);

    FrmBoard selectByPrimaryKey(Integer boardId);

    int updateByPrimaryKeySelective(FrmBoard record);

    int updateByPrimaryKey(FrmBoard record);
}
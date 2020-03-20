package com.elearning.dao.forum;

import com.elearning.pojo.forum.FrmBoardAdmin;
import com.elearning.pojo.forum.FrmBoardAdminKey;

public interface FrmBoardAdminMapper {
    int deleteByPrimaryKey(FrmBoardAdminKey key);

    int insert(FrmBoardAdmin record);

    int insertSelective(FrmBoardAdmin record);

    FrmBoardAdmin selectByPrimaryKey(FrmBoardAdminKey key);

    int updateByPrimaryKeySelective(FrmBoardAdmin record);

    int updateByPrimaryKey(FrmBoardAdmin record);
}
package com.elearning.dao.forum;

import com.elearning.pojo.forum.FrmReply;

public interface FrmReplyMapper {
    int deleteByPrimaryKey(Integer replyId);

    int insert(FrmReply record);

    int insertSelective(FrmReply record);

    FrmReply selectByPrimaryKey(Integer replyId);

    int updateByPrimaryKeySelective(FrmReply record);

    int updateByPrimaryKey(FrmReply record);
}
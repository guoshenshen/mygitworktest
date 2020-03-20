package com.elearning.dao.examManage;

import com.elearning.pojo.examManage.ExamUserReply;

import java.util.List;
import java.util.Map;

public interface ExamUserReplyMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ExamUserReply record);

    int insertSelective(ExamUserReply record);

    ExamUserReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamUserReply record);

    int updateByPrimaryKey(ExamUserReply record);

    List<ExamUserReply> findListByExamIDAndOperatorId(Map<String,Object> parmMap);



}
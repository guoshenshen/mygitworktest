package com.elearning.service.examManage;

import com.elearning.pojo.examManage.ExamUserReply;

import java.util.List;

public interface IExamUserReplyService {

    ExamUserReply selectByPrimaryKey(Integer id);

    List<ExamUserReply> findListByExamIDAndOperatorId(Integer eid, Integer operatorId);


}

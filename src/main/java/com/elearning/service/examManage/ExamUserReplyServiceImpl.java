package com.elearning.service.examManage;

import com.elearning.dao.examManage.ExamUserReplyMapper;
import com.elearning.pojo.examManage.ExamUserReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("examUserReplyService")
public class ExamUserReplyServiceImpl implements IExamUserReplyService {

    @Autowired
    private ExamUserReplyMapper examUserReplyMapper;

    @Override
    public ExamUserReply selectByPrimaryKey(Integer id) {
        return examUserReplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ExamUserReply> findListByExamIDAndOperatorId(Integer eid, Integer operatorId) {
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("examId",eid);
        parmMap.put("operatorId",operatorId);


        return examUserReplyMapper.findListByExamIDAndOperatorId(parmMap);
    }





}

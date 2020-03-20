package com.elearning.service.examManage;

import com.elearning.dao.examManage.ExamExamUserScoreMapper;
import com.elearning.pojo.examManage.ExamExamUserScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("examExamUserScoreService")
public class ExamExamUserScoreServiceImpl implements IExamExamUserScoreService {

    @Autowired
    private ExamExamUserScoreMapper examExamUserScoreMapper;

    @Override
    public ExamExamUserScore selectByPrimaryKey(Integer ID){

        return this.examExamUserScoreMapper.selectByPrimaryKey(ID);

    }

    @Override
    public List<ExamExamUserScore> findByExamId(Integer examId){

        return this.examExamUserScoreMapper.findByExamId(examId);

    }



}

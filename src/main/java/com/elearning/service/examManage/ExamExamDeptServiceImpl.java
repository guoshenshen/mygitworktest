package com.elearning.service.examManage;

import com.elearning.dao.examManage.ExamExamDeptMapper;
import com.elearning.pojo.examManage.ExamExamDept;
import com.elearning.vo.examManage.ExamDeptForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("examExamDeptService")
public class ExamExamDeptServiceImpl implements IExamExamDeptService {

    @Autowired
    private ExamExamDeptMapper examExamDeptMapper;

    @Override
    public ExamExamDept selectByPrimaryKey(Integer id) {

        return examExamDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ExamDeptForm> queryExamDeptByExamId(Integer eid) {

        List<ExamDeptForm> examDeptFormList = this.examExamDeptMapper.findByExamId(eid);
        for (ExamDeptForm examDeptForm:examDeptFormList){
            examDeptForm.setExam_Id(eid);
            examDeptFormList.add(examDeptForm);
        }

        return examDeptFormList;
    }

}

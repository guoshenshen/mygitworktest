package com.elearning.dao.examManage;

import com.elearning.pojo.examManage.ExamExamUser;
import com.elearning.vo.examManage.ExamUserForm;

import java.util.List;
import java.util.Map;

public interface ExamExamUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ExamExamUser record);

    int insertSelective(ExamExamUser record);

    ExamExamUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamExamUser record);

    int updateByPrimaryKey(ExamExamUser record);

    List<ExamExamUser> getListByExamIdAndOperatorId(Map<String,Object> parmMap);

    List<ExamUserForm> findByExamId(Integer eid);


}
package com.elearning.service.examManage;

import com.elearning.pojo.examManage.ExamExamInfo;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.vo.examManage.SimpleExanForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IExamExamInfoService {

    ExamExamInfo selectByPrimaryKey(Integer ID);

    int delExamInfoById(Integer ID);

    int updateExamInfo(ExamExamInfo record);

    //获取学员端考试列表
    List<ExamExamInfo> findByUserAndTime(Map<String,Object> parmMap,EosOperator eosOperator);

    Double findMaxScoreForSelfTest(Map<String, Object> map);

    //统计统一考试最高分
    Double findMaxScoreForUniteTest(Map<String, Object> map);

    List<ExamExamInfo> getListByTrainId(Integer trainId);

    List<ExamExamInfo> getListByTrainIdOrExamTitleOrExamType(Map<String,Object> map);

    List<ExamExamInfo> listExamInqueryResult(Map<String,Object> map);

    List<SimpleExanForm> searchOnlineReadingExam(Map<String,Object> map);

    void insertExamInfo(ExamExamInfo examInfo);

    void publishExam(HttpServletRequest request);

    void plusOnePersonOnJoinCount(Integer eid);

    void updateExamRegCount(Integer eid);



}

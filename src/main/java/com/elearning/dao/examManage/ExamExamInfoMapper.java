package com.elearning.dao.examManage;

import com.elearning.pojo.examManage.ExamExamInfo;
import com.elearning.vo.examManage.SimpleExanForm;

import java.util.List;
import java.util.Map;

public interface ExamExamInfoMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(ExamExamInfo record);

    int insertSelective(ExamExamInfo record);

    ExamExamInfo selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(ExamExamInfo record);

    int updateByPrimaryKeyWithBLOBs(ExamExamInfo record);

    int updateByPrimaryKey(ExamExamInfo record);

    List<ExamExamInfo> findAllExamByUserAndTime(Map<String,Object> parmMap);

    Double findMaxScoreForSelfTest(Map<String, Object> map);

    Double findMaxScoreForUniteTest(Map<String, Object> map);

    List<ExamExamInfo> getListByTrainId(Integer trainId);

    List<ExamExamInfo> getListByTrainIdOrExamTitleOrExamType(Map<String,Object> map);

    List<ExamExamInfo> listExamInqueryResult(Map<String,Object> map);

    List<SimpleExanForm> getListByExamIdForObject(Map<String,Object> map);

    List<SimpleExanForm> getListByExamIdForObject2(Map<String,Object> map);

}
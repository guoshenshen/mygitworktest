package com.elearning.dao.courseStudy;

import com.elearning.pojo.courseStudy.UscUsertliddayStudyInfo;

import java.util.List;
import java.util.Map;

public interface UscUsertliddayStudyInfoMapper {
    int deleteByPrimaryKey(Integer UTDID);

    int insert(UscUsertliddayStudyInfo record);

    int insertSelective(UscUsertliddayStudyInfo record);

    UscUsertliddayStudyInfo selectByPrimaryKey(Integer UTDID);

    int updateByPrimaryKeySelective(UscUsertliddayStudyInfo record);

    int updateByPrimaryKey(UscUsertliddayStudyInfo record);

    List<UscUsertliddayStudyInfo> findByMap(Map<String, Object> map);

    long getTotalAlreadyLearnedTimeByCourseIdAndUserIdInOneYear(Map<String, Object> map);

    long isAllreadyLearned(Map<String, Object> map);

    int findTotalCountbyCourseId(Long courseId);

    List<UscUsertliddayStudyInfo> getListByCourseId(Long courseId);
}
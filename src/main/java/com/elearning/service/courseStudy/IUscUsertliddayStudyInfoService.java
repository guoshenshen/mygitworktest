package com.elearning.service.courseStudy;

import com.elearning.pojo.courseStudy.UscUsertliddayStudyInfo;

import java.util.List;
import java.util.Map; /**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/2 16:46
 */
public interface IUscUsertliddayStudyInfoService {

    List<UscUsertliddayStudyInfo> findByMap(Map<String, Object> map);

    int deleteByPrimaryKey(Integer UTDID);

    int update(UscUsertliddayStudyInfo uscUserStudyInfo);

    int insert(UscUsertliddayStudyInfo uscUsertliddaystudyinfo);

    long getTotalAlreadyLearnedTimeByCourseIdAndUserIdInOneYear(Map<String, Object> map);

    boolean isAllreadyLearned(Map<String, Object> map);

    //返回某门课程总共被学习的次数
    int findTotalCountbyCourseId(Long courseId);


}

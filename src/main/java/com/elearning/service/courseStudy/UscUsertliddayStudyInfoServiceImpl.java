package com.elearning.service.courseStudy;

import com.elearning.dao.courseStudy.UscUsertliddayStudyInfoMapper;
import com.elearning.pojo.courseStudy.UscUsertliddayStudyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/2 16:46
 */
@Service("uscUsertliddayStudyInfoService")
public class UscUsertliddayStudyInfoServiceImpl implements IUscUsertliddayStudyInfoService{

    @Autowired
    private UscUsertliddayStudyInfoMapper uscUsertliddayStudyInfoMapper;


    @Override
    public List<UscUsertliddayStudyInfo> findByMap(Map<String, Object> map) {
        return uscUsertliddayStudyInfoMapper.findByMap(map);
    }

    @Override
    public int deleteByPrimaryKey(Integer UTDID) {
        return uscUsertliddayStudyInfoMapper.deleteByPrimaryKey(UTDID);
    }

    @Override
    public int update(UscUsertliddayStudyInfo uscUserStudyInfo) {
        return uscUsertliddayStudyInfoMapper.updateByPrimaryKeySelective(uscUserStudyInfo);
    }

    @Override
    public int insert(UscUsertliddayStudyInfo uscUsertliddaystudyinfo) {
        return uscUsertliddayStudyInfoMapper.insertSelective(uscUsertliddaystudyinfo);
    }

    @Override
    public long getTotalAlreadyLearnedTimeByCourseIdAndUserIdInOneYear(Map<String, Object> map) {
        return uscUsertliddayStudyInfoMapper.getTotalAlreadyLearnedTimeByCourseIdAndUserIdInOneYear(map);
    }

    @Override
    public boolean isAllreadyLearned(Map<String, Object> map) {
        Long result =  uscUsertliddayStudyInfoMapper.isAllreadyLearned(map);
        if(result == null || result == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public int findTotalCountbyCourseId(Long courseId){

        List<UscUsertliddayStudyInfo> uscUsertliddayStudyInfos = this.uscUsertliddayStudyInfoMapper.getListByCourseId(courseId);
        int sum = 0;
        if(uscUsertliddayStudyInfos.size()>0){
            sum = this.uscUsertliddayStudyInfoMapper.findTotalCountbyCourseId(courseId);
        }
        return  sum;
    }
}

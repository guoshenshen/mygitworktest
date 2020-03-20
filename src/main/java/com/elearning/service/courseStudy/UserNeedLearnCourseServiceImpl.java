package com.elearning.service.courseStudy;

import com.elearning.dao.courseStudy.UserNeedLearnCourseMapper;
import com.elearning.dao.courseStudy.UserTrainMapper;
import com.elearning.pojo.courseStudy.UserNeedLearnCourse;
import com.elearning.pojo.courseStudy.UserNeedLearnCourseKey;
import com.elearning.pojo.courseStudy.UserTrain;
import com.elearning.pojo.courseStudy.UserTrainKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userNeedLearnCourseService")
public class UserNeedLearnCourseServiceImpl implements IUserNeedLearnCourseService {

    @Autowired
    private UserNeedLearnCourseMapper userNeedLearnCourseMapper;



    //根据培训id进行删除
    public int deleteByTrainID(Integer trainId){

        return userNeedLearnCourseMapper.deleteByTrainID(trainId);
    }

    public int deleteByPrimaryKey(UserNeedLearnCourseKey key){

        return userNeedLearnCourseMapper.deleteByPrimaryKey(key);
    }

    @Override
    public UserNeedLearnCourse findCourseRelatedTrainId(Map<String,Object> map) {
        Integer result = 0 ;
        UserNeedLearnCourse userNeedLearnCourse = userNeedLearnCourseMapper.findCourseRelatedTrainId(map);

        return userNeedLearnCourse;
    }

    @Override
    public boolean findIfUserneedlearnTheCourse(Map<String, Object> map) {
        if(userNeedLearnCourseMapper.findCourseRelatedTrainId(map) != null ){
            return true;
        }
        return false;
    }

    @Override
    public int insert(UserNeedLearnCourse userNeedLearnCourse) {
        return userNeedLearnCourseMapper.insertSelective(userNeedLearnCourse);
    }


    @Override
    public void updateTrainName(int train_id,String trainName) {

        List<UserNeedLearnCourse> userNeedLearnCourseList = this.userNeedLearnCourseMapper.findListByTrainId(train_id);

        for(UserNeedLearnCourse temp : userNeedLearnCourseList){
            temp.setTrainName(trainName);
            this.userNeedLearnCourseMapper.updateByPrimaryKeySelective(temp);
        }
    }

    @Override
    public List<UserNeedLearnCourse> getListByOperatorAndTrainIdAndYear(Map<String,Object> map) {
        return userNeedLearnCourseMapper.getListByOperatorAndTrainIdAndYear(map);
    }

    @Override
    public UserNeedLearnCourse selectByPrimaryKey(UserNeedLearnCourseKey key){
        return userNeedLearnCourseMapper.selectByPrimaryKey(key);
    }

    @Override
    public int updateByPrimaryKey(UserNeedLearnCourse record){
        return userNeedLearnCourseMapper.updateByPrimaryKey(record);
    }

    public void deleteByOperatorIdTrainIdYear(Integer operatorId,Integer trainId,String year){

        Map<String,Object> parmM = new HashMap<>();
        parmM.put("operatorId",operatorId);
        parmM.put("trainId",trainId);
        parmM.put("year",year);

        List<UserNeedLearnCourse> userNeedLearnCourseList = this.userNeedLearnCourseMapper.getListByOperatorAndTrainIdAndYear(parmM);

        if(userNeedLearnCourseList.size()>0){
            for(int i=0;i<userNeedLearnCourseList.size();i++){
                this.userNeedLearnCourseMapper.deleteByPrimaryKey(userNeedLearnCourseList.get(i));
            }
        }
    }

    @Override
    public List<UserNeedLearnCourse> findAll() {
        return userNeedLearnCourseMapper.findAll();
    }

    //删除userneedlearncourse表某课程，培训相关联记录
    public void deleteByCourseIdTrainId(Long course_id,Integer trainId){
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",course_id);
        map.put("trainId",trainId);

        List<UserNeedLearnCourse> list = this.userNeedLearnCourseMapper.findByCourseIdTrainId(map);
        for(UserNeedLearnCourse temp : list){
            this.userNeedLearnCourseMapper.deleteByPrimaryKey(temp);
        }
    }


}

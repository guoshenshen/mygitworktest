package com.elearning.service.courseStudy;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.common.Tools;
import com.elearning.dao.courseStudy.UserTrainMapper;
import com.elearning.pojo.courseStudy.UserNeedLearnCourse;
import com.elearning.pojo.courseStudy.UserTrain;
import com.elearning.pojo.courseStudy.UserTrainKey;
import com.elearning.pojo.onlinetraining.OntOnlineTrainCourseUserArrange;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.onlinetraining.IOntOnlineTrainCourseUserArrangeService;
import com.elearning.service.pub.ICourseService;
import com.elearning.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service("userTrainService")
public class UserTrainServiceImpl implements IUserTrainService {

    @Autowired
    private UserTrainMapper userTrainMapper;

    @Autowired
    private IOntOnlineTrainCourseUserArrangeService ontOnlineTrainCourseUserArrangeService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IUserNeedLearnCourseService userNeedLearnCourseService;


    //根据主键进行查询
    public UserTrain selectByPrimaryKey(UserTrainKey key){

        return userTrainMapper.selectByPrimaryKey(key);
    }

    //根据主键进行删除
    public int deleteByPrimaryKey(UserTrainKey key){

        return userTrainMapper.deleteByPrimaryKey(key);
    }

    //根据培训id进行删除
    public int deleteByTrainID(Integer trainId){
        return userTrainMapper.deleteByTrainID(trainId);
    }

    //保存
    public int save(UserTrain record){

        return userTrainMapper.insertSelective(record);
    }


    public List<UserTrain> getListByTrainId(Integer trainId){

        return userTrainMapper.getListByTrainId(trainId);
    }

    public boolean ifOperatorJoinTrain(Integer operatorId, Integer trainId){
        if(operatorId == null || trainId == null){
            return false;
        }
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("operatorId",operatorId);
        parmMap.put("trainId",trainId);

        List<UserTrain> list = this.userTrainMapper.getListByTrainIdAndOperatorId(parmMap);
        if(null != list && list.size() > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ServiceResponse selectSingleCourse(Long courseId, HttpServletRequest request) {
        int result = 0 ;
        String _SerialNumber = ontOnlineTrainCourseUserArrangeService.getMaxID();
        HttpSession session = request.getSession(true);
        EosOperator eosoperator = (EosOperator) session.getAttribute(Constants.USERINFO_KEY);
        String year = session.getAttribute(Constants.YEAR_KEY).toString();
        int userID = eosoperator.getOperatorId();
        OntOnlineTrainCourseUserArrange ontOnlineTrainCourseUserArrange = new OntOnlineTrainCourseUserArrange(
                                                            _SerialNumber, -1, "slected Courses by"
                                                            + eosoperator.getUserId()
                                                        );
        Set<Course> courseSet = new HashSet();
        Set<EosOperator> eosoperatorSet = new HashSet<EosOperator>();
        Course course = courseService.getCourse(courseId);
        String courseName = course.getCourseName();
        Double classHour = course.getClassHour();
        courseSet.add(course);
        UserNeedLearnCourse userNeedLearnCourse = new UserNeedLearnCourse(userID,-1,year,0,courseId,
                "选修课程培训",Byte.parseByte("0"),"",courseName,classHour,"");
        result = userNeedLearnCourseService.insert(userNeedLearnCourse);
        int selectedTimes=0;
        if(course.getSelectedTimes()!=null){
            selectedTimes=course.getSelectedTimes();
        }
        course.setSelectedTimes(selectedTimes+1);
        this.courseService.updateCourse(course);
        ontOnlineTrainCourseUserArrange.setCourseSet(courseSet);
        eosoperatorSet.add(eosoperator);
        ontOnlineTrainCourseUserArrange.setEosoperatorSet(eosoperatorSet);
        result = ontOnlineTrainCourseUserArrangeService.insert(ontOnlineTrainCourseUserArrange);
        UserTrainKey userTrainKey = new UserTrainKey(userID,-1,year);
        if(userTrainMapper.selectByPrimaryKey(userTrainKey) == null ){
            try {
                UserTrain selecttrain = new UserTrain(userID,-1,year,
                        "选修课程培训",0,"",0,
                        Tools.stringToDate(year+"-01-01"),
                        Tools.stringToDate(year+"-12-31"),Byte.parseByte("0"),"");
                result = userTrainMapper.insertSelective(selecttrain);
            }catch (Exception e){
            }
        }
        if(result >0){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    public void updateTrainInfo(int train_id,String trainName,String startTime,String endTime,String sponsorName,int trainWay,Boolean isStationTrain){

        List<UserTrain> userTrainList = this.userTrainMapper.getListByTrainId(train_id);
        for(UserTrain temp : userTrainList){
            temp.setTrainName(trainName);
            //temp.setStartTime(Tools.stringToDate(startTime));
            temp.setStartTime(DateTimeUtil.strToDate(startTime,"yyyy-MM-dd HH:mm"));
            temp.setEndTime(DateTimeUtil.strToDate(endTime,"yyyy-MM-dd HH:mm"));
            temp.setSponsorName(sponsorName==null?"":sponsorName);
            temp.setTrainWay(trainWay);
            temp.setIsStationTrain(Byte.valueOf(String.valueOf(isStationTrain==true?1:0)));
            this.userTrainMapper.updateByPrimaryKeySelective(temp);
        }
    }



}

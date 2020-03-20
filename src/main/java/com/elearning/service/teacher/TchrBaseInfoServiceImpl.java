package com.elearning.service.teacher;

import com.elearning.common.ServiceResponse;
import com.elearning.common.Tools;
import com.elearning.dao.teacher.TchrBaseInfoMapper;
import com.elearning.pojo.coursemanage.CourseType;
import com.elearning.pojo.pub.DDictionary;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.teacher.TchrBaseInfo;
import com.elearning.pojo.teacher.TchrTeacherUseCourse;
import com.elearning.service.coursemanage.ICourseTypeService;
import com.elearning.service.pub.IDDictionaryService;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.util.DateTimeUtil;
import com.elearning.vo.teacher.TchrBaseInfoForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/29 17:05
 */
@Service("tchrBaseInfoService")
public class TchrBaseInfoServiceImpl implements ITchrBaseInfoService{

    @Autowired
    private TchrBaseInfoMapper tchrBaseInfoMapper;

    @Autowired
    private IDDictionaryService dictionaryService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private ICourseTypeService courseTypeService;

    @Autowired
    private ITchrTeacherUseCourseService tchrTeacherUseCourseService;

    @Autowired
    private IEosOperatorService eosOperatorService;


    @Override
    public TchrBaseInfo findById(int teacherId) {
        return tchrBaseInfoMapper.selectByPrimaryKey(teacherId);
    }

    /**
     * 新增课程时教师列表
     * @param map
     * @return
     */
    @Override
    public ServiceResponse getTeacherByConditionByPage(Map<String, Object> map) {
        PageHelper.startPage((Integer)map.get("startIndex"),(Integer)map.get("count"));
        List<TchrBaseInfo> tchrBaseInfoList = tchrBaseInfoMapper.findByCondition(map);
        PageInfo pageInfo = new PageInfo(tchrBaseInfoList);
        pageInfo.setList(tchrBaseInfoList);
        return ServiceResponse.createBySuccess(pageInfo);
    }


    /**
     * 判断教师名称是否存在
     * @param teacherName
     * @param orgId
     * @return
     */
    @Override
    public ServiceResponse judgeTeacher(String teacherName, Long orgId) {
        Map<String,Object> map = new HashMap<>();
        map.put("teacherNameEq",teacherName);
        map.put("orgId",orgId);
        List<TchrBaseInfo> tchrBaseInfoList = tchrBaseInfoMapper.findByCondition(map);
        if(tchrBaseInfoList != null && tchrBaseInfoList.size() > 0){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public TchrBaseInfoForm getTeacherInfoForm(TchrBaseInfo tempInfo){
        TchrBaseInfoForm teacherInfoForm=new TchrBaseInfoForm();
        if(tempInfo.getBirthDate()!=null){
            teacherInfoForm.setBirthDate(DateTimeUtil.dateToStr(tempInfo.getBirthDate()));
        }
        teacherInfoForm.setIsShared(Byte.valueOf((tempInfo.getIsShared()==true?"1":"0")));
        teacherInfoForm.setTeacherId(tempInfo.getId());
        teacherInfoForm.setDegree(tempInfo.getDegree());
        teacherInfoForm.setEmail(tempInfo.getEmail());
        if(tempInfo.getHireStyle()!=null){
            teacherInfoForm.setEmployedStyle(tempInfo.getHireStyle().intValue()==0?"内":"外");
            teacherInfoForm.setInner(tempInfo.getHireStyle().intValue()==0?"内":"外");
        }else{
            teacherInfoForm.setEmployedStyle("外");
            teacherInfoForm.setInner("外");
        }

        teacherInfoForm.setGender(this.dictionaryService.getDDictionaryByCodeAndParentCode(String.valueOf(tempInfo.getGender()), "1130"));
        teacherInfoForm.setGraduateSchool(tempInfo.getGraduateSchool());
        teacherInfoForm.setCost(tempInfo.getCost());
        teacherInfoForm.setWorkPlace(tempInfo.getWorkPlace());
        teacherInfoForm.setTitle(tempInfo.getTitle());
        teacherInfoForm.setTelephone(tempInfo.getTelephone());
        teacherInfoForm.setTeacherName(tempInfo.getTeacherName());
        teacherInfoForm.setMajorSubject(tempInfo.getMajorSubject());
        teacherInfoForm.setScore(tempInfo.getScore());
        teacherInfoForm.setRelatedMaterailPath(tempInfo.getRelatedMaterailPath());
        teacherInfoForm.setRelatedMaterailName(tempInfo.getRelatedMaterailType()==null?"无":"下载");
        teacherInfoForm.setRelatedMaterailSize(tempInfo.getRelatedMaterailSize());
        teacherInfoForm.setRelatedMaterailType(tempInfo.getRelatedMaterailType());
        teacherInfoForm.setPost(tempInfo.getPost());

        if(tempInfo.getOrgId() != null){
            if(this.eosorgTOrganizationService.findById(tempInfo.getOrgId()) != null){
                teacherInfoForm.setIsUnderScope(this.eosorgTOrganizationService.findById(tempInfo.getOrgId()).getOrgName());
            }
        }

        teacherInfoForm.setExpertAreaId(tempInfo.getExpertAreaId());
        teacherInfoForm.setSubExpertAreaId(tempInfo.getSubExpertAreaId());
        teacherInfoForm.setOpenScope(tempInfo.getOpenScope());
        teacherInfoForm.setHeadPic(tempInfo.getHeadPic());
        Integer courseTypeId = tempInfo.getExpertAreaId();
        CourseType coursetype = this.courseTypeService.selectByPrimaryKey(courseTypeId);
        String expertAreaName = "";
        if(coursetype!=null){
            expertAreaName= coursetype.getCourseTypeName().toString();
        }
        teacherInfoForm.setExpertAreaName(expertAreaName);
        if(tempInfo.getIsShared()!=null && tempInfo.getOpenScope()!=null && tempInfo.getTenantId()!=null){
            DDictionary ddic=this.dictionaryService.findTrainByCodeAndTenantIdReturnDDictionary(String.valueOf(tempInfo.getOpenScope()), tempInfo.getTenantId());
            if(ddic==null){
                ddic=this.dictionaryService.findTrainByCodeAndTenantIdReturnDDictionary(String.valueOf(tempInfo.getOpenScope()), 0);
            }
            teacherInfoForm.setOpenStatus(tempInfo.getIsShared()==false ? "未推荐":"已推荐("+ddic.getName()+")");
        }
        if(tempInfo.getIsShared()!=null){
            if(!tempInfo.getIsShared()){
                teacherInfoForm.setOpenStatus("未推荐");
            }else{
                DDictionary dic = this.dictionaryService.findTrainByCodeAndTenantIdReturnDDictionary(String.valueOf(tempInfo.getOpenScope()), tempInfo.getTenantId());
                if(dic!=null){
                    String openStatus = "已推荐("+dic.getName()+")";
                    teacherInfoForm.setOpenStatus(openStatus);
                }

            }
        }else{
            teacherInfoForm.setOpenStatus("未推荐");
        }

        StringBuffer courseNameStr=new StringBuffer();   //擅长课程
        StringBuffer courseWareStr=new StringBuffer();   //相关课件

        List<TchrTeacherUseCourse> teacherUseCourseList=this.tchrTeacherUseCourseService.findByTeacherId(tempInfo.getId());

        if(teacherUseCourseList!=null&&!teacherUseCourseList.isEmpty() && teacherUseCourseList.size()>0){
            for(TchrTeacherUseCourse teacherCourse : teacherUseCourseList){
                if(teacherCourse.getIsCourse()==0){
                    courseNameStr.append(teacherCourse.getCourseName()).append(";");
                } else{
                    courseWareStr.append(teacherCourse.getCourseName()).append(";");
                }
            }
            if(courseNameStr!=null&&!courseNameStr.toString().equals("")){
                teacherInfoForm.setCourseName(courseNameStr.toString().substring(0, courseNameStr.toString().lastIndexOf(";")));
            }
            if(courseWareStr!=null&&!courseWareStr.toString().equals("")){
                teacherInfoForm.setCourseWareName(courseWareStr.toString().substring(0, courseWareStr.toString().lastIndexOf(";")));
            }
        }
        teacherInfoForm.setIsNoted(tempInfo.getIsNoted());
        teacherInfoForm.setIsSendToIndex(tempInfo.getIsSendToIndex());
        teacherInfoForm.setIsLinkSchedule(tempInfo.getIsLinkSchedule());

        if(tempInfo.getOperatorId() != null){
            Integer operatorId = tempInfo.getOperatorId();
            EosOperator operator = this.eosOperatorService.findById(operatorId);
            teacherInfoForm.setOperatorName(operator.getOperatorName());
        }

        teacherInfoForm.setRemark(tempInfo.getRemark());
        teacherInfoForm.setKeyWords(tempInfo.getKeyWords());
        teacherInfoForm.setIsOpenEmail(tempInfo.getIsOpenEmail());
        teacherInfoForm.setIsOpenTelephone(tempInfo.getIsOpenTelephone());
        return teacherInfoForm;
    }
}

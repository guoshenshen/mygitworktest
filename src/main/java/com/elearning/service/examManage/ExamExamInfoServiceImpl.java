package com.elearning.service.examManage;

import com.elearning.common.Constants;
import com.elearning.common.MyUtils;
import com.elearning.common.Tools;
import com.elearning.dao.examManage.ExamExamInfoMapper;
import com.elearning.pojo.examManage.ExamExamInfo;
import com.elearning.pojo.examManage.ExamExamReg;
import com.elearning.pojo.pub.*;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.pub.IUserVOrganizationService;
import com.elearning.vo.examManage.SimpleExanForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("examExamInfoService")
public class ExamExamInfoServiceImpl implements IExamExamInfoService {

    @Autowired
    private ExamExamInfoMapper examExamInfoMapper;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IUserVOrganizationService userVOrganizationService;

    @Autowired
    private IExamUserReplyService examUserReplyService;

    @Autowired
    private IExamExamRegService examExamRegService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IExamExamUserScoreService examExamUserScoreService;

    @Override
    public ExamExamInfo selectByPrimaryKey(Integer ID) {
        return examExamInfoMapper.selectByPrimaryKey(ID);
    }

    @Override
    public int delExamInfoById(Integer ID) {
        return examExamInfoMapper.deleteByPrimaryKey(ID);
    }

    @Override
    public int updateExamInfo(ExamExamInfo record) {
        return examExamInfoMapper.updateByPrimaryKeySelective(record);
    }

    //获取学员端考试列表
    @Override
    public List<ExamExamInfo> findByUserAndTime(Map<String,Object> parmMap, EosOperator eosOperator) {
        String systemTime=parmMap.get("systemTime").toString();
        Integer operatorId=eosOperator.getOperatorId();
        String orgStr = "";
        EosorgTEmployee eosorgTEmployee = this.eosorgTEmployeeService.findById(operatorId);
        Integer orgId = eosorgTEmployee.getOrgID();
        EosorgTOrganization eosorginfo=this.eosorgTOrganizationService.getEosorgTOrganizationById(orgId);
        if(eosorginfo.getOrgSEQ()!=null){
            String parentIdStr=eosorginfo.getOrgSEQ().replace(".", ",");
            String[] parentIdArr=parentIdStr.split(",");
            ArrayList<Integer> orgList=new ArrayList<>();
            for(int j=0;j<parentIdArr.length;j++) {
                if(parentIdArr[j]!=null && !parentIdArr[j].equals("")){
                    Integer _porgId=Integer.parseInt(parentIdArr[j]);
                    orgList.add(_porgId);
                }
            }
            //查找用户所在虚拟机构下的机构id和上级id
            List<UserVOrganization> userVorganizationList = this.userVOrganizationService.getUserVorganizationListByOperatorId(operatorId);
            for(UserVOrganization userVOrganization:userVorganizationList){
                Integer vorgId = userVOrganization.getOrgID();
                orgList.add(vorgId);

                //下面这个方法在原项目中没有实现体
                //this.eosorgTOrganizationService.getParentOrgIdList(vorgId, orgList);
            }
            orgStr= Tools.getAllValuesFromList(orgList);
        }
        List<ExamExamInfo> examList;
        PageHelper.startPage((Integer) parmMap.get("startIndex"),(Integer) parmMap.get("count"));
        parmMap.put("orgStr",orgStr);
        parmMap.put("operatorId",eosOperator.getOperatorId());

        List<ExamExamInfo> lastExamList=new ArrayList<>();
        try {

            //--sql语句还未写完
            examList = this.examExamInfoMapper.findAllExamByUserAndTime(parmMap);

            PageInfo pageInfo = new PageInfo(examList);
            pageInfo.setList(examList);

            examList = pageInfo.getList();

            for(ExamExamInfo examInfo:examList) {

                //-------实体类中能否直接加字段
                examInfo.setEnterflag(0);
                int examId=examInfo.getID();
                if( null != examInfo && 1 == examInfo.getIsPublish().intValue() && examInfo.getStartTime().toString().indexOf(systemTime)>-1){
                    List examUserReplyList = this.examUserReplyService.findListByExamIDAndOperatorId(examId, operatorId);
                    if(examUserReplyList.size()>0) {
                        examInfo.setUserExamStatus(Constants.S_EXAMPARTSTATUS_YES);
                    }else{
                        examInfo.setUserExamStatus(Constants.S_EXAMPARTSTATUS_NO);
                    }
                    if(examInfo.getStartTime().after(new Date())==false){
                        examInfo.setEnterflag(1);
                    }
                }
                List<ExamExamReg> examRegList = this.examExamRegService.findByUserIdAndExamStatus(operatorId, new Integer(1051));
                for(ExamExamReg examReg:examRegList) {
                    if(examReg.getExamID()==examId)examInfo.setUserExamStatus(Constants.S_EXAMAUDIT_AUDITING);
                }
                String trainName = "";
                if(examInfo.getTrainId()!=null){
                    Integer trainId = examInfo.getTrainId();
                    Train train = this.onlineTrainingService.findById(trainId);
                    trainName = train.getTrainName();
                }
                examInfo.setTrainName(trainName);
                lastExamList.add(examInfo);
            }

        } catch (Exception e) {
            System.out.println("获取学员端考试列表失败!");
            e.printStackTrace();
        }

        return lastExamList;
    }

    @Override
    public Double findMaxScoreForSelfTest(Map<String, Object> map) {
        return examExamInfoMapper.findMaxScoreForSelfTest(map);
    }

    @Override
    public Double findMaxScoreForUniteTest(Map<String, Object> map) {
        Double dd =  this.examExamInfoMapper.findMaxScoreForUniteTest(map);
        if(dd == null){
            return 0.0;
        }else{
            return dd;
        }
    }

    @Override
    public List<ExamExamInfo> getListByTrainId(Integer trainId) {
        return examExamInfoMapper.getListByTrainId(trainId);
    }

    @Override
    public List<ExamExamInfo> getListByTrainIdOrExamTitleOrExamType(Map<String,Object> map) {

        return examExamInfoMapper.getListByTrainIdOrExamTitleOrExamType(map);
    }

    @Override
    public List<ExamExamInfo> listExamInqueryResult(Map<String,Object> map) {

        return examExamInfoMapper.listExamInqueryResult(map);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void insertExamInfo(ExamExamInfo examInfo){

        this.examExamInfoMapper.insertSelective(examInfo);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<SimpleExanForm> searchOnlineReadingExam(Map<String,Object> map) {

        List<SimpleExanForm> list = new ArrayList<>();

        if(null!=map.get("examId") && !"".equals(map.get("examId"))){
            int examId = (Integer)map.get("examId");
            ExamExamInfo examInfo = this.examExamInfoMapper.selectByPrimaryKey(examId);
            if(examInfo.getIfRepeatExam()!=null&&examInfo.getIfRepeatExam()==1){
                list = this.examExamInfoMapper.getListByExamIdForObject(map);
            }else{
                list = this.examExamInfoMapper.getListByExamIdForObject2(map);
            }
        }

        return list;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void publishExam(HttpServletRequest request){

        String examForTrainFlag = request.getParameter("examForTrainFlag");
        Object trainId = null;
        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            trainId = request.getSession().getAttribute("trainId");//从seesion中获得当前培训ID号
            if(trainId!=null){
                TrainWithBLOBs train = this.onlineTrainingService.findById((Integer)trainId);
                request.setAttribute("train", train);
            }
        }

        String eidString = request.getParameter("eid");
        Integer eid = Integer.valueOf(eidString);
        ExamExamInfo examInfo = this.examExamInfoMapper.selectByPrimaryKey(eid);

        if(examInfo.getIsPublish()==0){
            examInfo.setIsPublish(new Integer(1));
        } else{
            if(this.examExamUserScoreService.findByExamId(eid).size()==0){
                examInfo.setIsPublish(0);
            } else{
                request.setAttribute("alertString", "该考试已经有人作答，无法取消发布!");
            }
        }

        this.examExamInfoMapper.updateByPrimaryKeySelective(examInfo);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void plusOnePersonOnJoinCount(Integer eid){

        ExamExamInfo examInfo = this.examExamInfoMapper.selectByPrimaryKey(eid);
        Integer joinCount = examInfo.getJoinCount();
        if(!MyUtils.isNull(joinCount)) {
            examInfo.setJoinCount(Integer.valueOf(joinCount.intValue()+1));
        }else{
            examInfo.setJoinCount(new Integer(1));
        }
        this.examExamInfoMapper.updateByPrimaryKeySelective(examInfo);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void updateExamRegCount(Integer eid){

        int regCount = this.examExamRegService.findRegCount(eid, Constants.I_AUDIT_NOTAUDIT);
        ExamExamInfo examInfo = this.examExamInfoMapper.selectByPrimaryKey(eid);
        if(!MyUtils.isNull(examInfo)) {
            examInfo.setRegCount(regCount);
            this.examExamInfoMapper.updateByPrimaryKeySelective(examInfo);
        }

    }


}

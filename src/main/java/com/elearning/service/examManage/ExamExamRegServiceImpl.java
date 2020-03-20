package com.elearning.service.examManage;

import com.elearning.common.Constants;
import com.elearning.common.MyUtils;
import com.elearning.dao.examManage.ExamExamRegMapper;
import com.elearning.pojo.examManage.ExamExamReg;
import com.elearning.pojo.examManage.ExamExamUser;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("examExamRegService")
public class ExamExamRegServiceImpl implements IExamExamRegService {

    @Autowired
    private ExamExamRegMapper examExamRegMapper;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IExamExamUserService examExamUserService;

    @Autowired
    private IExamExamInfoService examExamInfoService;

    @Override
    public ExamExamReg selectByPrimaryKey(Integer ID) {
        return examExamRegMapper.selectByPrimaryKey(ID);
    }

    @Override
    public ExamExamReg findByExamRegId(Integer examRegId) {
        return examExamRegMapper.selectByPrimaryKey(examRegId);
    }

    @Override
    public List<ExamExamReg> findByUserIdAndExamStatus(Integer employeeID, Integer examStatus) {
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("employeeID",employeeID);
        parmMap.put("examStatus",examStatus);

        return examExamRegMapper.findByUserIdAndExamStatus(parmMap);
    }

    @Override
    public void auditExamRegByIds(HttpServletRequest request) {

        String examForTrainFlag = request.getParameter("examForTrainFlag");
        Object trainId = null;
        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            //从seesion中获得当前培训ID号
            trainId = request.getSession().getAttribute("trainId");
            if(trainId != null){
                TrainWithBLOBs train = this.onlineTrainingService.findById((Integer)trainId);
                request.setAttribute("train", train);
            }
        }

        String[] idStrings = request.getParameterValues("selectbox");
        Integer eid = Integer.valueOf(request.getParameter("eid"));

        if(idStrings.length>0){
            for(int i= 0; i<idStrings.length;i++) {
                Integer examRegId = Integer.valueOf(idStrings[i]);
                ExamExamReg examReg = this.examExamRegMapper.selectByPrimaryKey(examRegId);

                if(null != examReg.getExamStatus() && examReg.getExamStatus().intValue() != 1052){
                    examReg.setExamStatus(Constants.I_AUDIT_PASS);
                    this.examExamRegMapper.updateByPrimaryKeySelective(examReg);
                }

                ExamExamUser examUser = new ExamExamUser();
                examUser.setExam_id(eid);
                examUser.setOperatorId(examReg.getEmployeeID());

                if(!this.examExamUserService.isExistExamUser(examUser)){
                    this.examExamUserService.saveExamUser(examUser);
                    this.examExamInfoService.plusOnePersonOnJoinCount(eid);
                }
            }

            this.examExamInfoService.updateExamRegCount(eid);
            request.setAttribute("eid", eid);
        }
    }

    @Override
    public int findRegCount(Integer eid, Integer examStatus) {

        Map<String,Object> parm = new HashMap<>();
        parm.put("examID",eid);
        parm.put("examStatus",examStatus);

        List<ExamExamReg> examExamRegList = this.examExamRegMapper.getListByExamIdAndExamStatus(parm);

        if(examExamRegList.size() > 0){
            return examExamRegList.size();
        }else{
            return 0;
        }
    }



}

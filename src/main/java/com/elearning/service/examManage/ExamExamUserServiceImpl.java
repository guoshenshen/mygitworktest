package com.elearning.service.examManage;

import com.elearning.common.ServiceResponse;
import com.elearning.dao.examManage.ExamExamUserMapper;
import com.elearning.pojo.examManage.ExamExamUser;
import com.elearning.vo.examManage.ExamUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("examExamUserService")
public class ExamExamUserServiceImpl implements IExamExamUserService {

    @Autowired
    private ExamExamUserMapper examExamUserMapper;

    @Override
    public ExamExamUser selectByPrimaryKey(Integer id) {
        return this.examExamUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveExamUser(ExamExamUser record) {
        return this.examExamUserMapper.insertSelective(record);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse removeExamUser(HttpServletRequest request) {

        String[] operatorIdList = request.getParameterValues("operatorId");
        String eidString = request.getParameter("eid");
        Integer eid = Integer.parseInt(eidString);
        try {
            this.deleteExamDeptByExamIdAndUserId(eid, operatorIdList);
            return ServiceResponse.createBySuccess();
        } catch (Exception e) {
            e.printStackTrace();
            String message = "考试人员删除失败";
            return ServiceResponse.createByErrorMessage(message);
        }
    }

    @Transactional(rollbackFor = {Exception.class })
    public void deleteExamDeptByExamIdAndUserId(Integer eid , String[] userIdStrings) {

        for(int i=0 ; i<userIdStrings.length ; i++){
            ExamExamUser examUser = this.findByExamIDAndDeptID(eid, Integer.valueOf(userIdStrings[i]));
            if(examUser != null){
                this.examExamUserMapper.deleteByPrimaryKey(examUser.getId());
            }
        }

    }

    public ExamExamUser findByExamIDAndDeptID(Integer eid , Integer UserId){

        ExamExamUser result = null;

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("exam_id",eid);
        parmMap.put("operatorId",UserId);

        List<ExamExamUser> resultList=this.examExamUserMapper.getListByExamIdAndOperatorId(parmMap);
        if(resultList!=null && resultList.size()>0){
            result = resultList.get(0);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse insertExamUser(HttpServletRequest request) {

        String[] operatorIdList = request.getParameterValues("operatorId");
        //List<ExamExamUser> examUserListToAdd = new ArrayList<>();
        String eidString = request.getParameter("eid");
        Integer eid = Integer.parseInt(eidString);

        try {
            for(String operatorIdStr : operatorIdList){
                ExamExamUser examUser = new ExamExamUser();
                examUser.setExam_id(eid);
                examUser.setOperatorId(Integer.parseInt(operatorIdStr));
                //examUserListToAdd.add(examUser);  // 添加考试人员

                if(this.findByExamIDAndDeptID(eid , Integer.parseInt(operatorIdStr)) == null){
                    this.examExamUserMapper.insert(examUser);
                }
            }
            return ServiceResponse.createBySuccess();
        } catch (Exception e) {
            e.printStackTrace();
            String message = "考试人员指派失败";
            return ServiceResponse.createByErrorMessage(message);
        }

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public boolean isExistExamUser(ExamExamUser examUser) {

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("exam_id",examUser.getExam_id());
        parmMap.put("operatorId",examUser.getOperatorId());

        List<ExamExamUser> list = this.examExamUserMapper.getListByExamIdAndOperatorId(parmMap);
        if(list.size()>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<ExamUserForm> queryExamUserByExamId(Integer eid){
        List<ExamUserForm> examUserFormList  = this.examExamUserMapper.findByExamId(eid);
        for(ExamUserForm examUserForm : examUserFormList){
            examUserForm.setExamId(eid);
            examUserFormList.add(examUserForm);
        }

        return examUserFormList;

    }


}
